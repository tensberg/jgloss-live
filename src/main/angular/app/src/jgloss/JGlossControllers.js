(function(){
  'use strict';

  angular
       .module('jglossControllers', [ 'jglossServices' ])
       .controller('JGlossController', [
          'Lookup',
          JGlossController
       ]);

  function JGlossController( Lookup) {
	  var self = this;
	  
	  self.text = "";
	  self.dictionaryResults = {};
	  
	  self.lookup = function() {
		  var result = Lookup.get({ text : self.text }, function() {
			  self.dictionaryResults = result.dictionaryResults;
		  });
	  }
  }

})();
