// Configuration
const API_BASE = 'http://localhost';
const PORTS = {
    temperature: 8080,
    humidite: 8085,
    niveauliquide: 8084,
    orchestrateur: 8079,
    refroidissement: 8081,
    extraction: 8086,
    log: 8083,
    citernes: 8082
};

let currentCiterneId = null;
let refreshInterval;

// Initialize on page load
document.addEventListener('DOMContentLoaded', async () => {
    console.log('Initializing Cave à Vin Dashboard...');
    
    // Load citernes list first
    await loadCiternesList();
    
    console.log('Current citerne ID after loading list:', currentCiterneId);
    
    // Setup citerne selector
    const citerneSelect = document.getElementById('citerne-select');
    citerneSelect.addEventListener('change', (e) => {
        const newId = parseInt(e.target.value);
        console.log('Citerne selection changed. Raw value:', e.target.value, 'Parsed:', newId);
        
        if (isNaN(newId)) {
            console.error('Invalid citerne ID selected:', e.target.value);
            return;
        }
        
        currentCiterneId = newId;
        console.log('Citerne changed to:', currentCiterneId);
        refreshAllData();
    });
    
    // Initial data load only if we have a valid citerne
    if (currentCiterneId !== null) {
        console.log('Loading initial data for citerne:', currentCiterneId);
        refreshAllData();
        
        // Auto-refresh every 5 seconds
        refreshInterval = setInterval(refreshAllData, 5000);
    } else {
        console.error('No citerne available, skipping data load');
    }
    
    console.log('Dashboard initialized successfully!');
});

// Load Citernes List
async function loadCiternesList() {
    console.log('Loading citernes list from:', `${API_BASE}:${PORTS.citernes}/citernes/list`);
    try {
        const response = await fetch(`${API_BASE}:${PORTS.citernes}/citernes/list`);
        console.log('Response status:', response.status);
        
        if (response.ok) {
            const citernes = await response.json();
            console.log('Citernes received:', citernes);
            const citerneSelect = document.getElementById('citerne-select');
            
            // Clear existing options
            citerneSelect.innerHTML = '';
            
            if (citernes && citernes.length > 0) {
                citernes.forEach(citerne => {
                    const option = document.createElement('option');
                    option.value = citerne.citerneID;
                    option.textContent = citerne.citerneName || `Citerne ${citerne.citerneID}`;
                    citerneSelect.appendChild(option);
                    console.log('Added option - ID:', citerne.citerneID, 'Type:', typeof citerne.citerneID, 'Name:', citerne.citerneName);
                });
                
                // Set current citerne to the first one
                currentCiterneId = parseInt(citernes[0].citerneID);
                console.log('Citernes loaded successfully:', citernes.length, 'citernes');
                console.log('Set currentCiterneId to:', currentCiterneId, 'Type:', typeof currentCiterneId);
                
                if (isNaN(currentCiterneId)) {
                    console.error('ERROR: currentCiterneId is NaN after parsing!', citernes[0]);
                    currentCiterneId = null;
                }
            } else {
                // No citernes found, add placeholder
                console.warn('No citernes found in response');
                const option = document.createElement('option');
                option.value = '';
                option.textContent = 'Aucune citerne disponible';
                citerneSelect.appendChild(option);
                currentCiterneId = null;
            }
        } else {
            console.error('Error loading citernes list, status:', response.status);
            // Add default option on error
            const citerneSelect = document.getElementById('citerne-select');
            citerneSelect.innerHTML = '<option value="">Erreur de chargement</option>';
            currentCiterneId = null;
        }
    } catch (error) {
        console.error('Error loading citernes list:', error);
        console.error('Error details:', error.message);
        // Add default option on error
        const citerneSelect = document.getElementById('citerne-select');
        citerneSelect.innerHTML = '<option value="">Erreur de chargement</option>';
        currentCiterneId = null;
    }
}

// Refresh all data
async function refreshAllData() {
    if (currentCiterneId === null || currentCiterneId === undefined) {
        console.warn('No citerne selected (null or undefined), skipping refresh');
        return;
    }
    
    if (isNaN(currentCiterneId)) {
        console.error('ERROR: currentCiterneId is NaN! Cannot refresh data.');
        return;
    }
    
    console.log('Refreshing data for citerne:', currentCiterneId, 'Type:', typeof currentCiterneId);
    await Promise.all([
        loadTemperature(),
        loadHumidity(),
        loadLiquidLevel(),
        loadLastEvent(),
        loadCoolingStatus(),
        loadExtractionStatus()
    ]);
}

// Load Temperature
async function loadTemperature() {
    try {
        const url = `${API_BASE}:${PORTS.temperature}/temperature/last/${currentCiterneId}`;
        console.log('Fetching temperature from:', url);
        const response = await fetch(url);
        console.log('Temperature response status:', response.status);
        
        if (response.ok) {
            const text = await response.text();
            console.log('Temperature raw response:', text);
            const temp = parseInt(text);
            console.log('Temperature parsed:', temp, 'Type:', typeof temp);
            document.getElementById('temperature-value').textContent = temp;
        } else if (response.status === 404) {
            console.warn('No temperature data found for citerne', currentCiterneId);
            document.getElementById('temperature-value').textContent = 'N/A';
        } else {
            console.error('Failed to load temperature, status:', response.status);
            document.getElementById('temperature-value').textContent = '--';
        }
    } catch (error) {
        console.error('Error loading temperature:', error);
        console.error('Error details:', error.message);
        document.getElementById('temperature-value').textContent = 'Erreur';
    }
}

// Load Humidity
async function loadHumidity() {
    try {
        const url = `${API_BASE}:${PORTS.humidite}/humidite/last/${currentCiterneId}`;
        console.log('Fetching humidity from:', url);
        const response = await fetch(url);
        console.log('Humidity response status:', response.status);
        
        if (response.ok) {
            const text = await response.text();
            console.log('Humidity raw response:', text);
            const humidity = parseFloat(text);
            console.log('Humidity parsed:', humidity, 'Type:', typeof humidity);
            document.getElementById('humidity-value').textContent = humidity.toFixed(1);
        } else if (response.status === 404) {
            console.warn('No humidity data found for citerne', currentCiterneId);
            document.getElementById('humidity-value').textContent = 'N/A';
        } else {
            console.error('Failed to load humidity, status:', response.status);
            document.getElementById('humidity-value').textContent = '--';
        }
    } catch (error) {
        console.error('Error loading humidity:', error);
        console.error('Error details:', error.message);
        document.getElementById('humidity-value').textContent = 'Erreur';
    }
}

// Load Liquid Level
async function loadLiquidLevel() {
    try {
        const url = `${API_BASE}:${PORTS.niveauliquide}/niveauliquide/last/${currentCiterneId}`;
        console.log('Fetching liquid level from:', url);
        const response = await fetch(url);
        console.log('Liquid level response status:', response.status);
        
        if (response.ok) {
            const text = await response.text();
            console.log('Liquid level raw response:', text);
            const level = parseFloat(text);
            console.log('Liquid level parsed:', level, 'Type:', typeof level);
            document.getElementById('liquid-value').textContent = level.toFixed(1);
        } else if (response.status === 404) {
            console.warn('No liquid level data found for citerne', currentCiterneId);
            document.getElementById('liquid-value').textContent = 'N/A';
        } else {
            console.error('Failed to load liquid level, status:', response.status);
            document.getElementById('liquid-value').textContent = '--';
        }
    } catch (error) {
        console.error('Error loading liquid level:', error);
        console.error('Error details:', error.message);
        document.getElementById('liquid-value').textContent = 'Erreur';
    }
}

// Load Last Event
async function loadLastEvent() {
    try {
        const url = `${API_BASE}:${PORTS.log}/log/list/${currentCiterneId}`;
        console.log('Fetching last event from:', url);
        const response = await fetch(url);
        console.log('Last event response status:', response.status);
        
        if (response.ok) {
            const logs = await response.json();
            console.log('Logs received:', logs);
            if (logs && logs.length > 0) {
                const lastLog = logs[0];
                const eventText = `${lastLog.actionType || 'Info'}: ${lastLog.observation || 'Aucune observation'}`;
                document.getElementById('last-event').textContent = eventText;
                console.log('Last event loaded:', eventText);
            } else {
                console.warn('No logs found for citerne', currentCiterneId);
                document.getElementById('last-event').textContent = 'Aucun événement récent';
            }
        } else if (response.status === 404) {
            console.warn('No logs found for citerne', currentCiterneId);
            document.getElementById('last-event').textContent = 'Aucun événement récent';
        } else {
            console.error('Failed to load logs, status:', response.status);
            document.getElementById('last-event').textContent = 'Erreur de chargement';
        }
    } catch (error) {
        console.error('Error loading last event:', error);
        console.error('Error details:', error.message);
        document.getElementById('last-event').textContent = 'Erreur de chargement';
    }
}

// Load Cooling Status
async function loadCoolingStatus() {
    try {
        const url = `${API_BASE}:${PORTS.refroidissement}/refroidissement/state/${currentCiterneId}`;
        console.log('Fetching cooling status from:', url);
        const response = await fetch(url);
        console.log('Cooling status response status:', response.status);
        
        if (response.ok) {
            const text = await response.text();
            console.log('Cooling status raw response:', text);
            const isActive = text === 'true' || text === true;
            console.log('Cooling status parsed:', isActive, 'Type:', typeof isActive);
            updateActionCard('cooling', isActive);
            console.log('Cooling status loaded:', isActive);
        } else if (response.status === 404) {
            console.warn('No cooling status found for citerne', currentCiterneId);
            updateActionCard('cooling', false);
        } else {
            console.error('Failed to load cooling status, status:', response.status);
            updateActionCard('cooling', false);
        }
    } catch (error) {
        console.error('Error loading cooling status:', error);
        console.error('Error details:', error.message);
        updateActionCard('cooling', false);
    }
}

// Load Extraction Status
async function loadExtractionStatus() {
    try {
        const url = `${API_BASE}:${PORTS.extraction}/extraction/state/${currentCiterneId}`;
        console.log('Fetching extraction status from:', url);
        const response = await fetch(url);
        console.log('Extraction status response status:', response.status);
        
        if (response.ok) {
            const text = await response.text();
            console.log('Extraction status raw response:', text);
            const isActive = text === 'true' || text === true;
            console.log('Extraction status parsed:', isActive, 'Type:', typeof isActive);
            updateActionCard('extraction', isActive);
            console.log('Extraction status loaded:', isActive);
        } else if (response.status === 404) {
            console.warn('No extraction status found for citerne', currentCiterneId);
            updateActionCard('extraction', false);
        } else {
            console.error('Failed to load extraction status, status:', response.status);
            updateActionCard('extraction', false);
        }
    } catch (error) {
        console.error('Error loading extraction status:', error);
        console.error('Error details:', error.message);
        updateActionCard('extraction', false);
    }
}

// Update Action Card
function updateActionCard(type, isActive) {
    const card = document.getElementById(`${type}-card`);
    const status = document.getElementById(`${type}-status`);
    
    if (isActive) {
        card.classList.add('active');
        card.classList.remove('inactive');
        status.textContent = 'ACTIF';
    } else {
        card.classList.add('inactive');
        card.classList.remove('active');
        status.textContent = 'INACTIF';
    }
}

// Send Temperature
async function sendTemperature() {
    const input = document.getElementById('input-temperature');
    const value = parseInt(input.value);
    
    if (isNaN(value)) {
        alert('Veuillez entrer une valeur valide');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE}:${PORTS.temperature}/temperature/add?valeur=${value}&citerneID=${currentCiterneId}`, {
            method: 'POST'
        });
        
        if (response.ok) {
            console.log('Temperature sent successfully:', value);
            input.value = '';
            showNotification('Température ajoutée avec succès!', 'success');
            setTimeout(() => {
                loadTemperature();
                triggerDecision();
            }, 500);
        } else {
            showNotification('Erreur lors de l\'ajout de la température', 'error');
        }
    } catch (error) {
        console.error('Error sending temperature:', error);
        showNotification('Erreur de connexion', 'error');
    }
}

// Send Humidity
async function sendHumidity() {
    const input = document.getElementById('input-humidity');
    const value = parseFloat(input.value);
    
    if (isNaN(value)) {
        alert('Veuillez entrer une valeur valide');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE}:${PORTS.humidite}/humidite/add?valeur=${value}&citerneID=${currentCiterneId}`, {
            method: 'POST'
        });
        
        if (response.ok) {
            console.log('Humidity sent successfully:', value);
            input.value = '';
            showNotification('Humidité ajoutée avec succès!', 'success');
            setTimeout(() => {
                loadHumidity();
                triggerDecision();
            }, 500);
        } else {
            showNotification('Erreur lors de l\'ajout de l\'humidité', 'error');
        }
    } catch (error) {
        console.error('Error sending humidity:', error);
        showNotification('Erreur de connexion', 'error');
    }
}

// Send Liquid Level
async function sendLiquidLevel() {
    const input = document.getElementById('input-liquid');
    const value = parseFloat(input.value);
    
    if (isNaN(value)) {
        alert('Veuillez entrer une valeur valide');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE}:${PORTS.niveauliquide}/niveauliquide/add?valeur=${value}&citerneID=${currentCiterneId}`, {
            method: 'POST'
        });
        
        if (response.ok) {
            console.log('Liquid level sent successfully:', value);
            input.value = '';
            showNotification('Niveau de liquide ajouté avec succès!', 'success');
            setTimeout(() => {
                loadLiquidLevel();
                triggerDecision();
            }, 500);
        } else {
            showNotification('Erreur lors de l\'ajout du niveau de liquide', 'error');
        }
    } catch (error) {
        console.error('Error sending liquid level:', error);
        showNotification('Erreur de connexion', 'error');
    }
}

// Trigger Decision (Orchestrateur)
async function triggerDecision() {
    console.log('Triggering orchestrateur decision for citerne:', currentCiterneId);
    
    try {
        const response = await fetch(`${API_BASE}:${PORTS.orchestrateur}/orchestrateur/decision/${currentCiterneId}`);
        
        if (response.ok) {
            const result = await response.text();
            console.log('Orchestrateur result:', result);
            showNotification('Analyse effectuée avec succès!', 'success');
            
            // Refresh data after 1 second
            setTimeout(refreshAllData, 1000);
        } else {
            showNotification('Erreur lors de l\'analyse', 'error');
        }
    } catch (error) {
        console.error('Error triggering decision:', error);
        showNotification('Erreur de connexion avec l\'orchestrateur', 'error');
    }
}

// Show Notification
function showNotification(message, type) {
    // Create notification element
    const notification = document.createElement('div');
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 1rem 1.5rem;
        background: ${type === 'success' ? '#10b981' : '#ef4444'};
        color: white;
        border-radius: 10px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
        z-index: 10000;
        font-weight: 600;
        animation: slideIn 0.3s ease;
    `;
    notification.textContent = message;
    
    // Add to page
    document.body.appendChild(notification);
    
    // Remove after 3 seconds
    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

// Add animations for notifications
const style = document.createElement('style');
style.textContent = `
    @keyframes slideIn {
        from {
            transform: translateX(400px);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
    
    @keyframes slideOut {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(400px);
            opacity: 0;
        }
    }
`;
document.head.appendChild(style);
