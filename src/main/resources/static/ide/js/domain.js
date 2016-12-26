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

    this.data = {
        wires: [],
        data: []
    };
    
    /**
     * Attaches data to this circuit (i.e. the actual circuit data)
     *
     *    data - Previous data (represented as a dictionary of information)
     *           This data is saved in the form defined by the .qstudio spec,
     *           which can be found at https://github.com/vontell/qstudio/wiki/Circuit-Data-Type
     *
     */
    this.attachData = function(data) {

        this.data = data;

    };

    /**
     * Returns the filename for this cicruit
     */
    this.getFilename = function() {
        return this.filename;
    };

    /**
     * Returns the size of this circuit in number of wires
     * NOTE: This is not the actual height of the circuit (as
     *       the length may include truncation lines)
     */
    this.getCircuitHeight = function() {
        return this.data.wires.length;
    };


    /**
     * Add a new line to the circuit, which may either
     * be a new wire (wireType = 0) or a truncation (wireType = 1)
     *
     *     wireType - The type of wire (0 if an actual wire, or 1 if a truncation)
     *     index - The index of the wire
     *
     */
    this.addLine = function(wireType, index) {

        // Attach the new wire to the datatype
        this.data.wires.splice(index, 0, {
            type: wireType,
            qubit: null
        });

    };

}