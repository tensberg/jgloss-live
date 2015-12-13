(function(){
  'use strict';

  angular
       .module('jglossControllers', [ 'jglossServices' ])
       .controller('JGlossController', [ '$location', JGlossController ])
       .controller('LookupController', [
          'LookupService',
          LookupController
       ])
       .controller('GlossController', [ GlossController ])
       ;

  function JGlossController($location) {
      this.showView = function(view) {
          $location.path('/' + view)
      }
  }
  
  function LookupController(LookupService) {
	  var self = this;
	  
	  self.text = "";
	  self.dictionaryResults = {};
	  
	  self.lookup = function() {
		  var result = LookupService.get({ text : self.text }, function() {
			  self.dictionaryResults = result.dictionaryResults;
		  });
	  }
  }
  
  function GlossController() {
  }

})();
