package core.util;

import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of static methods for completing certain mathematical computations
 * @author Aaron Vontell
 * @version 0.1
 */
public class Mathematics {

    /** Euler's constant Pi.
     * http://www.cs.arizona.edu/icon/oddsends/pi.htm
     */
    public static final Apfloat PI = new Apfloat(
            "3.14159265358979323846264338327950288419716939937510582097494459230781640628620"+
            "899862803482534211706798214808651328230664709384460955058223172535940812848111"+
            "745028410270193852110555964462294895493038196442881097566593344612847564823378"+
            "678316527120190914564856692346034861045432664821339360726024914127372458700660"+
            "631558817488152092096282925409171536436789259036001133053054882046652138414695"+
            "194151160943305727036575959195309218611738193261179310511854807446237996274956"+
            "735188575272489122793818301194912983367336244065664308602139494639522473719070"+
            "217986094370277053921717629317675238467481846766940513200056812714526356082778"+
            "577134275778960917363717872146844090122495343014654958537105079227968925892354"+
            "201995611212902196086403441815981362977477130996051870721134999999837297804995"+
            "105973173281609631859502445945534690830264252230825334468503526193118817101000"+
            "313783875288658753320838142061717766914730359825349042875546873115956286388235"+
            "378759375195778185778053217122680661300192787661119590921642019893809525720106"+
            "548586327886593615338182796823030195203530185296899577362259941389124972177528"+
            "347913151557485724245415069595082953311686172785588907509838175463746493931925"+
            "506040092770167113900984882401285836160356370766010471018194295559619894676783"+
            "744944825537977472684710404753464620804668425906949129331367702898915210475216"+
            "205696602405803815019351125338243003558764024749647326391419927260426992279678"+
            "235478163600934172164121992458631503028618297455570674983850549458858692699569"+
            "092721079750930295532116534498720275596023648066549911988183479775356636980742"+
            "654252786255181841757467289097777279380008164706001614524919217321721477235014") ;

    /**
     * Computes the continued fraction for the given number, up to a given number
     * of iterations (simple continued fraction).
     * More information: http://mathworld.wolfram.com/SimpleContinuedFraction.html
     * @param number The number to compute the continued fraction for, as a high precision
     *               float
     * @param iterations The maximum number of iterations to compute this continued
     *                   fraction for
     * @return the simple continued fraction in abbreviated notation
     */
    public static int[] continuedFraction(Apfloat number, int iterations) {

        List<Integer> fraction = new ArrayList<Integer>();

        int iter_count = 0;
        while (iter_count < iterations) {

            // Get the remainder and integer parts
            int integer = number.intValue();
            Apfloat remainder = number.mod(Apfloat.ONE);

            fraction.add(integer);
            number = Apfloat.ONE.divide(remainder);
            iter_count++;

        }

        // TODO: Gotta be another way... is this too slow?
        int[] result = new int[fraction.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = fraction.get(i);
        }

        return result;

    }

    /**
     * Computes the continued fraction for the given number, up to a given number
     * of iterations (simple continued fraction).
     * More information: http://mathworld.wolfram.com/SimpleContinuedFraction.html
     * @param number The number to compute the continued fraction for, as a float
     * @param iterations The maximum number of iterations to compute this continued
     *                   fraction for
     * @return the simple continued fraction in abbreviated notation
     */
    public static int[] continuedFraction(float number, int iterations) {

        List<Integer> fraction = new ArrayList<Integer>();

        int iter_count = 0;
        while (iter_count < iterations && number < Float.POSITIVE_INFINITY) {

            // Get the remainder and integer parts
            int integer = (int) number;
            float remainder = number % 1;

            fraction.add(integer);
            number = 1.0f / remainder;
            iter_count++;

        }

        // TODO: Gotta be another way... is this too slow?
        int[] result = new int[fraction.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = fraction.get(i);
        }

        return result;

    }

    /**
     * Computes the continued fraction for the given number, up to a given number
     * of iterations (simple continued fraction).
     * More information: http://mathworld.wolfram.com/SimpleContinuedFraction.html
     * @param number The number to compute the continued fraction for, as a BigDecimal
     * @param iterations The maximum number of iterations to compute this continued
     *                   fraction for
     * @return the simple continued fraction in abbreviated notation
     */
    public static int[] continuedFraction(BigDecimal number, int iterations) {
        throw new RuntimeException("Not yet implemented error");
    }

}
