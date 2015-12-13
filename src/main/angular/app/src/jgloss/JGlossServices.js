(function(){
  'use strict';

  angular.module('jglossServices', [ 'ngResource' ])
         .factory('LookupService', ['$resource', LookupService]);

  /**
   * Lookup of a word in the dictionary.
   */
  function LookupService($resource) {
      return $resource('/api/lookup/:text');
  }

})();
