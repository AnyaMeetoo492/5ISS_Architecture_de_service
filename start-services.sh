#!/bin/bash

# Script pour démarrer tous les microservices en ordre

set -e

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SERVICES=(
    "Configuration"
    "Decouverte"
    "Citernes"
    "Extraction"
    "Humidite"
    "Log"
    "NiveauLiquide"
    "Temperature"
    "Refroidissement"
    "Orchestrateur"
    "Interface"
)

LOG_DIR="$PROJECT_ROOT/logs"
mkdir -p "$LOG_DIR"

echo "=========================================="
echo "Mise à jour des dépendances..."
echo "=========================================="

cd "$PROJECT_ROOT"
mvn clean install -U -DskipTests

if [ $? -ne 0 ]; then
    echo "Erreur lors de la mise à jour. Arrêt du script."
    exit 1
fi

echo ""
echo "=========================================="
echo "Construction de tous les packages..."
echo "=========================================="

cd "$PROJECT_ROOT"
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "Erreur lors de la construction. Arrêt du script."
    exit 1
fi

echo ""
echo "=========================================="
echo "Démarrage de tous les microservices..."
echo "=========================================="

# Fonction pour arrêter les services
cleanup() {
    echo ""
    echo "=========================================="
    echo "Arrêt de tous les microservices..."
    echo "=========================================="
    for service in "${SERVICES[@]}"; do
        if pgrep -f "Microservices/$service" > /dev/null 2>&1; then
            echo "Arrêt de $service..."
            pkill -f "Microservices/$service" || true
        fi
    done
    echo "Tous les services ont été arrêtés."
    
    echo ""
    echo "=========================================="
    echo "Nettoyage du projet..."
    echo "=========================================="
    cd "$PROJECT_ROOT"
    mvn clean
    echo "Nettoyage terminé."
    exit 0
}

# Capturer les signaux d'interruption
trap cleanup SIGINT SIGTERM

# Démarrer chaque service en arrière-plan
for service in "${SERVICES[@]}"; do
    SERVICE_PATH="$PROJECT_ROOT/Microservices/$service"
    LOG_FILE="$LOG_DIR/${service}.log"
    
    if [ ! -d "$SERVICE_PATH" ]; then
        echo "Répertoire non trouvé: $SERVICE_PATH"
        continue
    fi
    
    echo "Démarrage de $service..."
    cd "$SERVICE_PATH"
    nohup mvn spring-boot:run > "$LOG_FILE" 2>&1 &
    SERVICE_PID=$!
    echo "✓ $service démarré (PID: $SERVICE_PID)"
    echo "  Logs: $LOG_FILE"
    
    # Petit délai entre les démarrages
    sleep 2
done

echo ""
echo "=========================================="
echo "Tous les services sont en cours d'exécution!"
echo "=========================================="
echo ""
echo "Fichiers logs disponibles:"
for service in "${SERVICES[@]}"; do
    echo "  - $LOG_DIR/${service}.log"
done
echo ""
echo "Pour arrêter les services, appuyez sur Ctrl+C"
echo "=========================================="

# Maintenir le script actif
wait
