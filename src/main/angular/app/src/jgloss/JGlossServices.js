(function(){
  'use strict';

  angular.module('jglossServices', [ 'ngResource' ])
         .factory('Lookup', ['$resource', Lookup]);

  /**
   * Lookup of a word in the dictionary.
   */
  function Lookup($resource) {
      return $resource('/api/lookup/:text');
  }

})();
