$(document).ready(function() {
  var typingTimer;
  var doneTypingInterval = 500;
  var $searchInput = $('#search-inputW');
  var $searchResults = $('#search-results');
  
  // écoute l'événement de saisie de l'utilisateur
  $searchInput.on('keyup', function() {
    clearTimeout(typingTimer);
    typingTimer = setTimeout(doneTyping, doneTypingInterval);
  });

  function doneTyping() {
    var query = $searchInput.val();
    if (query.length > 0) {
      // envoie une requête Ajax à la route de recherche en direct
      $.get('/produit/dynamic-search', { q: query }, function(data) {
        // affiche les résultats de recherche dans la vue
        $searchResults.html('');
        var produitsHtml = '<div class="produits-horizontal">';
        for (var i = 0; i < data.length; i++) {
          var img = data[i].image;
          var produitHtml = `
            <div class="produit">
              <img src="uploads/produit_img/${img}" alt="${img}" height="200">
              <h3>${data[i].nom}</h3>
              <p>${data[i].description}</p>
            </div>
          `;
          produitsHtml += produitHtml;
        }
        produitsHtml += '</div>';
        $searchResults.append(produitsHtml);

        // Ajoute le code CSS pour l'affichage horizontal
        var styleHtml = `
          <style>
            .produits-horizontal {
              display: flex;
              flex-wrap: wrap;
              justify-content: center;
            }
            .produit {
              width: 200px;
              margin: 10px;
            }
          </style>
        `;
        document.head.insertAdjacentHTML('beforeend', styleHtml);
      });
    } else {
      $searchResults.html('');
    }
  }
});