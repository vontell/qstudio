/* Factories for objects (which relies on the 'app' var as defined in ide.js) */

app.factory('Circuit', function() {
    
    /**
     * Constructor for a circuit which gets represented
     * on the frontend circuit layout. This is a default
     * board with no gates or components.
     *  
     *    filename - The name of the file (without extension)
     *
     */
    function Circuit(filename) {
        
        this.filename = filename;
        
    }
    
    /**
     * Attaches data to this circuit (i.e. the actual circuit data)
     *
     *    data - Previous data (represented as a dictionary of information)
     *
     */
    Circuit.prototype.attachData = function(data) {
        
        
        
    };
    
    /**
     * Returns the filename for this cicruit
     */
    Circuit.prototype.getFilename = function() {
        return this.filename;
    };
    
})