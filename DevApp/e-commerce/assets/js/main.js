/*
*   Fichier JS principal
*   Contient les fonctions JS utilisées sur toutes les pages
*/


// Variables
var categorieID; // ID de la catégorie cliquée
var lastDropdownID = null; // ID du dernier dropdown affiché

// On ajoute un event listener sur chaque lien de catégorie
document.querySelectorAll('#categorie-link').forEach(occurence => {
    occurence.addEventListener('click', (e) => {
        var categorieID = occurence.getAttribute('catID');
        displayDropdown(categorieID);
        console.log(categorieID);
    });
});

// Fonction qui affiche le dropdown correspondant à la catégorie cliquée
// Si le dropdown est déjà affiché, on le cache
// Si un autre dropdown est déjà affiché, on le cache avant d'afficher le nouveau
// Si aucun dropdown n'est affiché, on affiche le nouveau
// categoryID = ID de la catégorie cliquée
function displayDropdown(categorieID) {
    document.querySelectorAll('.navigation-dropdown').forEach(occurence => {
        if (occurence.getAttribute('dropdownID') == categorieID) {
            // Si le dropdown est déjà affiché, on le cache
            if (categorieID == lastDropdownID) {
                occurence.style.display = 'none';
                lastDropdownID = null;
                return;
            }
            // On cache le dernier dropdown affiché
            if (lastDropdownID != null) {
                document.querySelectorAll('.navigation-dropdown').forEach(occurence => {
                    if (occurence.getAttribute('dropdownID') == lastDropdownID) {
                        occurence.style.display = 'none';
                    }
                })
            }
            lastDropdownID = categorieID;
            // Affiche le dropdown
            occurence.style.display = 'flex';
        }
    })
}
