
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClosestAntennaPairExposedTests {

    // base case: empty pointsA
    @Test
    @Tag("score:2")
    @DisplayName("Base case test 1")
    void baseCaseTest1() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {p1, p2, p3};
        Point2D[] pointsB = {};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        assertEquals(Double.POSITIVE_INFINITY, closestAntennaPair.distance());
        assertEquals(0, closestAntennaPair.getCounter());
    }

    // base case: empty pointsB
    @Test
    @Tag("score:2")
    @DisplayName("Base case test 2")
    void baseCaseTest2() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {};
        Point2D[] pointsB = {p1, p2, p3};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        assertEquals(Double.POSITIVE_INFINITY, closestAntennaPair.distance());
        assertEquals(0, closestAntennaPair.getCounter());
    }

    // base case: one point in pointsA
    @Test
    @Tag("score:3")
    @DisplayName("Base case test 3")
    void baseCaseTest3() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {p2};
        Point2D[] pointsB = {p1,p3};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        //System.out.println(p2.distanceTo(p3));
        assertEquals(p2.distanceTo(p3), closestAntennaPair.distance());
        assertEquals(1, closestAntennaPair.getCounter());
    }

    // base case: one point in pointsB
    @Test
    @Tag("score:3")
    @DisplayName("Base case test 4")
    void baseCaseTest4() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D[] pointsA = {p1,p3};
        Point2D[] pointsB = {p2};
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
        assertEquals(p2.distanceTo(p3), closestAntennaPair.distance());
        assertEquals(1, closestAntennaPair.getCounter());
    }

    // recursive case: aPoints on the right of bPoints
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 1")
    void recursiveTest1() {
        Point2D p1 = new Point2D(0.1,1);
        Point2D p2 = new Point2D(-0.1,1);
        Point2D p3 = new Point2D(0.2,2);
        Point2D p4 = new Point2D(-0.2,2);
        Point2D p5 = new Point2D(0.3,3);
        Point2D p6 = new Point2D(-0.3,3);
        Point2D[] aPoints = {p1, p3, p5};
        Point2D[] bPoints = {p2, p4, p6};

        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
        assertTrue(pair.getCounter() > 1);
    }

    // recursive case: aPoints on the left of bPoints
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 2")
    void recursiveTest2() {
        Point2D p1 = new Point2D(0.1,1);
        Point2D p2 = new Point2D(-0.1,1);
        Point2D p3 = new Point2D(0.2,2);
        Point2D p4 = new Point2D(-0.2,2);
        Point2D p5 = new Point2D(0.3,3);
        Point2D p6 = new Point2D(-0.3,3);
        Point2D[] aPoints = {p2, p4, p6};
        Point2D[] bPoints = {p1, p3, p5};

        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
        assertTrue(pair.getCounter() > 1);
    }

    // recursive case: aPoints below bPoints
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 3")
    void recursiveTest3() {
        Point2D p1 = new Point2D(1,0.1);
        Point2D p2 = new Point2D(1,-0.1);
        Point2D p3 = new Point2D(2,0.2);
        Point2D p4 = new Point2D(2,-0.2);
        Point2D p5 = new Point2D(3,0.3);
        Point2D p6 = new Point2D(3,-0.3);
        Point2D[] aPoints = {p2, p4, p6};
        Point2D[] bPoints = {p1, p3, p5};


        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
    }

    // recursive case: aPoints above bPoints
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 4")
    void recursiveTest4() {
        Point2D p1 = new Point2D(1,0.1);
        Point2D p2 = new Point2D(1,-0.1);
        Point2D p3 = new Point2D(2,0.2);
        Point2D p4 = new Point2D(2,-0.2);
        Point2D p5 = new Point2D(3,0.3);
        Point2D p6 = new Point2D(3,-0.3);
        Point2D[] aPoints = {p1, p3, p5};
        Point2D[] bPoints = {p2, p4, p6};


        ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(0.2, pair.distance());
        assertTrue(pair.getCounter() > 1);
    }

    // recursive case: aPoints and bPoints are well mixed
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 5")
    void recursiveTest5() {
        Point2D p1 = new Point2D(-0.2, 0.4);
        Point2D p2 = new Point2D(0.15, -0.1);
        Point2D p3 = new Point2D(-0.06, 0.15);
        Point2D p4 = new Point2D(0.3, 0.5);
        Point2D p5 = new Point2D(-0.15, 0.25);
        Point2D p6 = new Point2D(0.15, 0.3);
        Point2D p7 = new Point2D(0.35, -0.1);

        Point2D[] aPoints = {p1, p3, p4, p7};
        Point2D[] bPoints = {p2, p5, p6};

        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
        assertEquals(p3.distanceTo(p5), closestAntennaPair.distance());
        assertTrue(closestAntennaPair.getCounter() > 1);
    }


    // Test with random points. Feel free to use this as a template to make your own tests!
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 6")
    void recursiveTest6() {
        // The test may take too long and potentially lead to a timeout error if n > 10000
        int n = 100;
        // Each point in A will take value in the box [aXMin, aXMax) x [aYMin, aYMax)
        // and each point in B will be in the box [bXMin, bXMax) x [bYMin, bYMax).
        // Feel free to modify the values to change the size and location of the boxes.
        // However, if your box is invalid, this test will fail with a RunTimeException.
        double aXMin = -1;
        double aXMax = 1;
        double aYMin = 1;
        double aYMax = 2;
        double bXMin = 1;
        double bXMax = 2;
        double bYMin = 0;
        double bYMax = 4;

        if (aXMax < aXMin || aYMax < aYMin || bXMax < bXMin || bYMax < bYMin) {
            throw new RuntimeException("Box constraint is not valid");
        }

        Point2D[] aPoints = new Point2D[n];
        Point2D[] bPoints = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double aX = aXMin + Math.random() * (aXMax - aXMin);
            double aY = aYMin + Math.random() * (aYMax - aYMin);
            double bX = bXMin + Math.random() * (bXMax - bXMin);
            double bY = bYMin + Math.random() * (bYMax - bYMin);
            aPoints[i] = new Point2D(aX, aY);
            bPoints[i] = new Point2D(bX, bY);
        }
        //ClosestAntennaPairBruteForce closestAntennaPair = new ClosestAntennaPairBruteForce(aPoints, bPoints);
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
        ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(aPoints, bPoints);
        assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
        System.out.println("counter "+closestAntennaPair.getCounter());

    }
    @Test
    @DisplayName("Random Points")
    void randomPoints() {
        int n = 200;
        Point2D[] arrayA = new Point2D[n];
        Point2D[] arrayB = new Point2D[n];
        for (int i = 0; i < n; i++) {
            float xA = new Random().nextFloat();
            float yA = new Random().nextFloat();
            Point2D pointA = new Point2D(xA, yA);
            float xB = new Random().nextFloat();
            float yB = new Random().nextFloat();
            Point2D pointB = new Point2D(xB, yB);
            arrayA[i] = pointA;
            arrayB[i] = pointB;
        }
        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(arrayA, arrayB);
        ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(arrayA, arrayB);
        assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
        assertTrue(closestAntennaPair.getCounter() > 1);
    }

    // Test with random points. Feel free to use this as a template to make your own tests!
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 6")
    void recursiveTest10() {
        // The test may take too long and potentially lead to a timeout error if n > 10000
        int n = 4;
        // Each point in A will take value in the box [aXMin, aXMax) x [aYMin, aYMax)
        // and each point in B will be in the box [bXMin, bXMax) x [bYMin, bYMax).
        // Feel free to modify the values to change the size and location of the boxes.
        // However, if your box is invalid, this test will fail with a RunTimeException.
        double aXMin = -1;
        double aXMax = 1;
        double aYMin = 1;
        double aYMax = 2;
        double bXMin = 1;
        double bXMax = 2;
        double bYMin = 0;
        double bYMax = 4;

        if (aXMax < aXMin || aYMax < aYMin || bXMax < bXMin || bYMax < bYMin) {
            throw new RuntimeException("Box constraint is not valid");
        }

        Point2D[] aPoints = new Point2D[n];
        Point2D[] bPoints = new Point2D[n];

        while(true) {
            for (int i = 0; i < n; i++) {
                double aX = aXMin + Math.random() * (aXMax - aXMin);
                double aY = aYMin + Math.random() * (aYMax - aYMin);
                double bX = bXMin + Math.random() * (bXMax - bXMin);
                double bY = bYMin + Math.random() * (bYMax - bYMin);
                aPoints[i] = new Point2D(aX, aY);
                bPoints[i] = new Point2D(bX, bY);
            }
            for (Point2D p : aPoints) {
                System.out.println(p.toString());
            }

            System.out.println("-----------------------------------");
            for (Point2D p : bPoints) {
                System.out.println(p.toString());
            }

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(aPoints, bPoints);
            assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
        }
    }
    // Deterministic Recursive case 6 - 1
    @Test
    @Tag("score:0")
    @DisplayName("Recursive case 6.1")
    void recursiveTest7() {
        Point2D[] test1 = new Point2D[]{
                new Point2D(0.3552057213706925,1.111976452298794),new Point2D(-0.5814194123796832,1.883755533960823),new Point2D(0.497787311454575,1.6604764944609287),new Point2D(0.11439661335467255,1.1848041005932972),new Point2D(-0.19990016100361752,1.7535047592006832),new Point2D(0.17217086798261128,1.4579604212890231),new Point2D(0.5141485094993461,1.1308829191753647),new Point2D(-0.8545083335470138,1.599357327146311),new Point2D(0.8034128743284505,1.6656310452354504),new Point2D(-0.23795087945687698,1.0896053431629062),new Point2D(-0.5214184715847936,1.7307745952476452),new Point2D(-0.8171312310883609,1.9223975180695487),new Point2D(-0.9833874741421575,1.0969741077014716),new Point2D(0.08002624122118007,1.6596405746667502),new Point2D(0.05843100444532978,1.1148840762361536),new Point2D(0.978156462741534,1.4920178826381383),new Point2D(-0.9194226644741172,1.1861467398917858),new Point2D(-0.19493129207769866,1.4338909963353847),new Point2D(-0.5638822478156531,1.7769277644104573),new Point2D(-0.9032190949625487,1.0314456543888426),new Point2D(0.46013036981776545,1.4778993631268),new Point2D(-0.19771456083589967,1.2129888718801989),new Point2D(-0.16492128401369133,1.8645776912125993),new Point2D(-0.8666412917915711,1.1603029697772005),new Point2D(0.9113855197160858,1.1619202033759728),new Point2D(0.40472717097939426,1.3299630904103799),new Point2D(-0.821480690735306,1.9670898013291696),new Point2D(-0.6900974258431716,1.0035242448575086),new Point2D(0.5819061065900331,1.534291498378403),new Point2D(-0.42200181181625407,1.995484403762804),new Point2D(0.8809785738181386,1.375588300789897),new Point2D(-0.5431605607159726,1.397929506093265),new Point2D(-0.256323982462342,1.1816021301431046),new Point2D(0.9676806037225318,1.4907670985248354),new Point2D(-0.22729366038198373,1.3160064192700571),new Point2D(0.3768409068053169,1.19645429369626),new Point2D(-0.8411163764990415,1.3877163624539952),new Point2D(0.8295713798126367,1.7081269015843579),new Point2D(-0.8431466463666468,1.6546832215182379),new Point2D(0.5914664581123281,1.1700354743926458),new Point2D(0.07851769039223666,1.2631988845452806),new Point2D(0.8427313905241922,1.5391949478436833),new Point2D(-5.263226615035421E-4,1.5766168776914031),new Point2D(-0.5293810783576598,1.007153641618812),new Point2D(0.8737763114118535,1.0973961683978088),new Point2D(-0.19465262483144685,1.610788339088563),new Point2D(-0.7476603937767197,1.7363740275188004),new Point2D(-0.25518742821649365,1.911031440045404),new Point2D(-0.6997121676227431,1.4331739831942096),new Point2D(-0.786593739048133,1.868436556404817),new Point2D(0.308569156009169,1.3816554841638755),new Point2D(0.4002536044962506,1.2944884924323068),new Point2D(-0.6048551421519512,1.364311148889196),new Point2D(-0.27781807059995867,1.5075644786512306),new Point2D(0.5910659516586094,1.9495181362231588),new Point2D(0.9567334515868384,1.0724521912071263),new Point2D(0.006750220460004952,1.5555496853572313),new Point2D(-0.509465439334436,1.7523314034916777),new Point2D(-0.7262283169096408,1.6352531894231612),new Point2D(0.12833402466228416,1.0521250774734785),new Point2D(-0.04252432657273397,1.4853010218414247),new Point2D(-0.591977548591957,1.982126436064933),new Point2D(0.13573111269963656,1.4339204425414658),new Point2D(-0.13305396384399049,1.9208082188532223),new Point2D(0.8391333211138621,1.4110085870786282),new Point2D(0.20482632114963173,1.7057201186322923),new Point2D(0.5479693813633462,1.1545391265432468),new Point2D(-0.21140057115122612,1.3313810555492114),new Point2D(0.4412588787037359,1.5571665938362163),new Point2D(0.4444861220064964,1.8830722341477826),new Point2D(-0.4068179251336197,1.9069347332385058),new Point2D(0.982606465227843,1.5854373197477993),new Point2D(-0.18779164460648268,1.0259951147180986),new Point2D(0.3994503368951676,1.0947960147189564),new Point2D(0.3182376186153202,1.5612422283407938),new Point2D(0.20351737237626466,1.4140772593290885),new Point2D(0.9530663878698609,1.9307713374812518),new Point2D(0.8094379301265489,1.059278360077501),new Point2D(-0.8805078056453666,1.680065432860589),new Point2D(-0.5541117109274865,1.3563561733355085),new Point2D(0.1443675502746964,1.2518171712261332),new Point2D(-0.47738763075770674,1.997456639754368),new Point2D(-0.7390827764111492,1.4232927143943548),new Point2D(-0.09813805276582199,1.2353890878353693),new Point2D(-0.09436988088224885,1.9151429724929667),new Point2D(-0.9804311643193593,1.3452513413519422),new Point2D(-0.47496065679508326,1.9255689957598623),new Point2D(-0.6984393742303059,1.2321118478528712),new Point2D(0.5877020994212456,1.5937476371330699),new Point2D(-0.17986679133859962,1.313655362125377),new Point2D(0.9966926795796167,1.6464237308004908),new Point2D(-0.3637081910282012,1.0566982155826379),new Point2D(0.5979200634397284,1.7315749312953477),new Point2D(0.7729739994818137,1.7964792956725812),new Point2D(0.9795840023173701,1.193055584965977),new Point2D(0.26456620672599596,1.7176334293741893),new Point2D(0.25808034001905433,1.2901259497692432),new Point2D(-0.7719731959103777,1.1257529745031816),new Point2D(-0.9543299725385106,1.1157728446435686),new Point2D(-0.5990717293555119,1.388444007419519)
        };

        Point2D[] test2 = new Point2D[]{
                new Point2D(1.030240413516348,0.5970853424466132),new Point2D(1.031378006737106,2.656999473324715),new Point2D(1.6124797655679493,3.720587786344463),new Point2D(1.9117719261472805,2.570797637696019),new Point2D(1.4262032334067465,2.6217529642673068),new Point2D(1.1683056532349552,2.802960859157595),new Point2D(1.8476531283302848,2.659495476958519),new Point2D(1.4185016394479746,2.9456240822625746),new Point2D(1.0705455251911937,1.836997741249554),new Point2D(1.7563263719924955,3.859184810445996),new Point2D(1.6466823479227402,1.7279828135487176),new Point2D(1.4847276194492427,0.5195969004353205),new Point2D(1.669202758888961,0.9364356341041624),new Point2D(1.0396389694554489,3.9069239266681914),new Point2D(1.758630034363867,2.355395426366108),new Point2D(1.3906079990818005,3.7007779271322323),new Point2D(1.2618699035532406,2.4904460537903015),new Point2D(1.4532040795323065,0.5482673899895487),new Point2D(1.3243170028500988,0.1135284095122353),new Point2D(1.2983622526539609,1.6700800837844856),new Point2D(1.7452253407809541,2.512700121315682),new Point2D(1.193819531061402,2.5703258720971665),new Point2D(1.776499283772913,0.6034672135692034),new Point2D(1.762799589976864,2.6157642511740096),new Point2D(1.2750610699574576,1.1673137536122447),new Point2D(1.7951021653377168,3.2500411851536315),new Point2D(1.8689513536903664,1.4830530811831837),new Point2D(1.495753834077052,2.6085614377331248),new Point2D(1.0473189295646521,0.6166263801500613),new Point2D(1.2063756343128371,1.2996203490443512),new Point2D(1.3344556692093954,2.6349741282872965),new Point2D(1.8844245664295487,3.117419975085372),new Point2D(1.9580333784448942,0.009603949184946448),new Point2D(1.8754646786569649,3.4525957736508412),new Point2D(1.6716983622178514,2.47548132590581),new Point2D(1.7636243087434482,3.1658773533973292),new Point2D(1.6421802931283125,1.610867326325697),new Point2D(1.335395520604532,2.6905094401850738),new Point2D(1.5610713125331017,2.4037288759001734),new Point2D(1.9419321510819418,0.12905750120413106),new Point2D(1.3914320717961817,2.070345757595426),new Point2D(1.7950025876502558,2.1102227207126196),new Point2D(1.4093803371968816,1.3917404409706813),new Point2D(1.9817536102499487,1.1712925858366932),new Point2D(1.4508190821332514,2.833554890347501),new Point2D(1.2334414553369253,2.4318504351554355),new Point2D(1.5234597571867163,2.7149997089953275),new Point2D(1.733286576888319,3.324370437136566),new Point2D(1.1942972817056796,1.6925005143266634),new Point2D(1.4986079867831807,3.1473213213438247),new Point2D(1.4126930509712698,2.8236663943917772),new Point2D(1.1223937610565409,0.20640631295856426),new Point2D(1.6792219736116665,3.4772063837475993),new Point2D(1.9094638105123105,3.309561669664433),new Point2D(1.7204399748892272,0.35441699104904245),new Point2D(1.692381166917373,1.3813402283563794),new Point2D(1.0797281369890421,3.887868057541912),new Point2D(1.862268024823365,3.9623053111527824),new Point2D(1.2843697554709474,3.282910425840474),new Point2D(1.3996829317995332,2.359058745765256),new Point2D(1.4990166096734945,0.467630820622547),new Point2D(1.940715106352838,2.2105285583211747),new Point2D(1.8257005548685532,0.21687917896219178),new Point2D(1.1634524178797299,3.236142328867654),new Point2D(1.66054683081259,0.7159931464794611),new Point2D(1.029979119684469,2.931509115245343),new Point2D(1.7673452913508512,1.829844887620316),new Point2D(1.6690595043299745,1.9772371574559737),new Point2D(1.9781357187524937,1.3428350156531255),new Point2D(1.9346640864461302,2.126510774851509),new Point2D(1.6809636136974033,2.4354591781250647),new Point2D(1.9254941171830793,0.3370523271643302),new Point2D(1.5049626916479735,0.6630712987021141),new Point2D(1.0006608491699311,3.913093456369004),new Point2D(1.8629201412504584,3.702469696589386),new Point2D(1.0766760937738828,0.42298198703455014),new Point2D(1.26148118097297,2.1319299354438814),new Point2D(1.240909781715023,1.158047420657895),new Point2D(1.5391489770753253,2.5103105335694247),new Point2D(1.9666712401951787,1.8983522659047734),new Point2D(1.4422361834463204,3.0208554171082063),new Point2D(1.46507015836639,1.7509527064928894),new Point2D(1.1989244569762862,0.9453186817745571),new Point2D(1.9588136488235683,1.8262935538647844),new Point2D(1.0015881121429566,2.2340369455545335),new Point2D(1.913095533409118,2.5183639964197724),new Point2D(1.5575381567197877,0.43757393404494405),new Point2D(1.508734223761174,3.1542370956967956),new Point2D(1.1183430138458,1.2655618357973384),new Point2D(1.8927153770472969,0.7786200479170415),new Point2D(1.647882150014332,2.0383656488636746),new Point2D(1.991295525951925,1.8390378334526294),new Point2D(1.0392888769668762,0.2971939532232559),new Point2D(1.920823109228589,1.5372782561068452),new Point2D(1.5409385097964883,2.8382829561364287),new Point2D(1.2814328560160229,2.6127206073420357),new Point2D(1.8016382461044804,3.4367767980697863),new Point2D(1.8687702259006609,2.3231743182129208),new Point2D(1.195174985767797,2.5259308768314166),new Point2D(1.5889015226485301,1.3888528441759882)
        };

        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(test1, test2);
        ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(test1, test2);
        assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
    }

    // Deterministic Recursive case 6 - 2
    @Test
    @Tag("score:0")
    @DisplayName("Recursive case 6.2")
    void recursiveTest8() {
        Point2D[] test1 = new Point2D[]{
                new Point2D(-0.9606214196311917,1.3694962786911344),new Point2D(-0.7186235404757002,1.6026948349762007),new Point2D(0.6584128621945531,1.8637591063589016),new Point2D(0.4809307251077093,1.9787776714936627),new Point2D(0.4188312008463708,1.2264734074993193),new Point2D(0.5565260318433973,1.3829467922168441),new Point2D(-0.2641055640413641,1.3994840968887106),new Point2D(0.8996084413700305,1.0708833201856023),new Point2D(-0.9445071904467472,1.232414273233427),new Point2D(0.9532888593967752,1.7354430657459194)
        };

        Point2D[] test2 = new Point2D[]{
                new Point2D(1.365502531004147,1.2218194443265165),new Point2D(1.7192743802517518,1.7478290222680712),new Point2D(1.169515090934012,0.05672803152356831),new Point2D(1.3477842460628273,2.8225663273383583),new Point2D(1.9903483214877702,3.168218550991195),new Point2D(1.3660178410572583,0.1595439748668004),new Point2D(1.8228056891527626,0.932942621460513),new Point2D(1.0640327898272632,2.7318196545271083),new Point2D(1.2342338602070826,3.412628377970816),new Point2D(1.1461928340026462,0.39131571169665014)
        };

        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(test1, test2);
        ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(test1, test2);
        assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
    }

    // Deterministic Recursive case 6 - 3
    @Test
    @Tag("score:0")
    @DisplayName("Recursive case 6.3")
    void recursiveTest9() {
        Point2D[] test1 = new Point2D[]{
                new Point2D(-0.7274044130902895,1.1209160714125734),new Point2D(0.4279452842417564,1.2015888845234262),new Point2D(-0.047780846707103475,1.3709874305385437),new Point2D(0.19605447198661508,1.346966343749465),new Point2D(0.2690859454357406,1.092569675082465),new Point2D(0.25514607768597086,1.2575401039316811),new Point2D(0.6782296879328924,1.2639865730538395),new Point2D(-0.9989768523409248,1.2837995030641371),new Point2D(0.31475002979074373,1.1454414421969705),new Point2D(0.31853878170134786,1.0290307403530399),new Point2D(-0.8115286963772952,1.6611744731798685),new Point2D(-0.9990754711902923,1.6047956486246675),new Point2D(-0.9982307218347681,1.1542397034345162),new Point2D(0.20244472059545227,1.2922332516105675),new Point2D(-0.46411985186746185,1.1428078700534494),new Point2D(0.1196875181573569,1.3630905226421477),new Point2D(0.05100294323930177,1.9569012844659377),new Point2D(-0.4031929084784269,1.064388124262134),new Point2D(-0.35449124895688233,1.3844673223771666),new Point2D(0.850027930938412,1.0206278805611406),new Point2D(-0.9804313344070965,1.7056962321094535),new Point2D(0.006724531950859092,1.5574478288020561),new Point2D(0.5885131561804302,1.744490100698057),new Point2D(0.6543887797282528,1.7006313860736497),new Point2D(0.3912356473497012,1.8159887479358374),new Point2D(0.09479745501610859,1.854376768108747),new Point2D(-0.2610479045094878,1.699059156751332),new Point2D(0.4986925299222258,1.412732801127609),new Point2D(-0.9503699762768256,1.0126544112801747),new Point2D(-0.34371339861574635,1.8081076587226295),new Point2D(0.8133986960926038,1.624948624354558),new Point2D(0.518906056033051,1.7507563889735094),new Point2D(-0.865746807575821,1.9741538332212412),new Point2D(-0.7729156708018268,1.6631539798758594),new Point2D(-0.20663493676262235,1.4299254271691977),new Point2D(-0.1795819545150814,1.5631596271067072),new Point2D(-0.7206879272501534,1.0742621678337874),new Point2D(0.3244982099541698,1.8998789149569633),new Point2D(-0.26946108039362326,1.5308070305824168),new Point2D(-0.589770896775984,1.0961300438409012),new Point2D(-0.7646376178614092,1.7214552183212217),new Point2D(-0.6791526375512258,1.8014202266168322),new Point2D(-0.5651671775066991,1.679175628281798),new Point2D(-0.7380460239998019,1.109338528690066),new Point2D(0.8737642298366406,1.795951698444421),new Point2D(-0.05012896694343216,1.2432716690131105),new Point2D(-0.9547536984061469,1.1347485176538044),new Point2D(0.5136366264766992,1.4643486322711028),new Point2D(0.5739499829726222,1.6844116417961035),new Point2D(-0.5540502945389203,1.205520572114527),new Point2D(-0.5164602001829477,1.68640467145657),new Point2D(-0.20162233457043177,1.6856823102126242),new Point2D(-0.006495043616851381,1.4122911749875877),new Point2D(-0.6838609174345327,1.507741501207618),new Point2D(0.7907218625306511,1.0208676324268915),new Point2D(0.7077743059496011,1.9945418204972278),new Point2D(0.2210605834627133,1.373853498285757),new Point2D(-0.25498399819017314,1.2665464013877794),new Point2D(0.3463481000256994,1.9390446041615488),new Point2D(-0.6118273242660079,1.315187885995973),new Point2D(0.3119997849158229,1.1598361705577678),new Point2D(0.6323371928659638,1.5757909364620022),new Point2D(-0.029366780398722536,1.9200217755258553),new Point2D(-0.7302670207939688,1.7539741594479554),new Point2D(-0.7930248536410214,1.982774452654347),new Point2D(0.26351053498291965,1.9094369105045812),new Point2D(0.909375367310177,1.5327160499527963),new Point2D(-0.07169333990334614,1.0510378493239578),new Point2D(0.03323944283651015,1.1999812829263838),new Point2D(0.6128787394774444,1.7470711669465742),new Point2D(-0.298307235535628,1.1951827549496588),new Point2D(-0.9198733477273893,1.5046467077398433),new Point2D(-0.2112112662323209,1.4341829833545627),new Point2D(0.27981538286361163,1.9220806001205535),new Point2D(-0.06942680975676119,1.7815798608033724),new Point2D(0.7093096310264941,1.8682021345505395),new Point2D(-0.5906820461807545,1.7918117319227407),new Point2D(0.05763396117383124,1.1924834953274965),new Point2D(0.8562356994569211,1.5767883136037983),new Point2D(0.32817087577622517,1.7198985838891234),new Point2D(-0.7820111046046294,1.678953884813657),new Point2D(0.13530860946558665,1.5344169813589696),new Point2D(-0.06769496259916652,1.0786886241459857),new Point2D(0.37035199749374526,1.805039384909719),new Point2D(0.7580108360645941,1.7149548264298065),new Point2D(-0.06799179985256099,1.6582809307507393),new Point2D(0.20479826006298585,1.1480937797666322),new Point2D(0.716760532485111,1.8510084626441436),new Point2D(0.008033676813438362,1.2436799225563866),new Point2D(-0.43555780410227696,1.2475379873928403),new Point2D(-0.40607568035665054,1.0063382779490642),new Point2D(-0.64567724147234,1.3548987114809457),new Point2D(0.9803532782022497,1.9424316437215392),new Point2D(-0.05298026760910135,1.420169803452488),new Point2D(-0.941304263783246,1.580523542467063),new Point2D(0.16754822834696936,1.84379622405471),new Point2D(0.7019297543825036,1.0285232834369857),new Point2D(0.5248203662096267,1.9127896867755152),new Point2D(0.644352847277633,1.6964195805065025),new Point2D(0.8629076148578527,1.1077211578565906)
        };

        Point2D[] test2 = new Point2D[]{
                new Point2D(1.9247339137643307,1.781145028407809),new Point2D(1.4501054318976196,1.2546401631691801),new Point2D(1.3811597240275213,1.5765877699301716),new Point2D(1.8720143327482397,3.533684284521116),new Point2D(1.9967112370111284,0.204788797518765),new Point2D(1.5589389344739677,3.349733762313635),new Point2D(1.5837930323206408,0.3264908860655793),new Point2D(1.316556810175434,3.8415016905885553),new Point2D(1.0075848855006604,1.560636941951731),new Point2D(1.4977251013340527,3.4559093969742256),new Point2D(1.6353148166958418,1.7357163331520447),new Point2D(1.8536394909977396,1.3040469574446738),new Point2D(1.8168432639741874,3.916746386091815),new Point2D(1.9026324416756635,0.5505814985536222),new Point2D(1.4317145096004738,3.9735137728857772),new Point2D(1.9767669696534873,1.1022375115845007),new Point2D(1.7878316402844958,2.4556998718393808),new Point2D(1.3563818735379773,1.8609744312377043),new Point2D(1.4069113261540986,1.540516881202632),new Point2D(1.1527450091174067,1.807486925191438),new Point2D(1.4818745132131703,0.8184779643939026),new Point2D(1.848970744413465,2.1806513292157343),new Point2D(1.0413296791130144,2.6829275808352007),new Point2D(1.659357598415236,1.0185459093501619),new Point2D(1.1063041733025654,3.088956264092933),new Point2D(1.1173029200772717,0.32666698511566183),new Point2D(1.3743808799817394,0.4473255544308268),new Point2D(1.5519500969822613,3.1389681482361924),new Point2D(1.2163954641530574,0.41902731766671275),new Point2D(1.3637251577009104,2.737346428782284),new Point2D(1.0208511188208709,3.3761029488235814),new Point2D(1.2589187713395835,3.2952008252705216),new Point2D(1.17120688574661,2.2943476759432566),new Point2D(1.8534697408985257,0.21068827252627154),new Point2D(1.191195581190292,3.8435239426346506),new Point2D(1.3061066272305708,1.3953010631345197),new Point2D(1.8433279280379395,2.440710127806234),new Point2D(1.891003354353986,3.783392793041737),new Point2D(1.215738933195718,2.0324091473988095),new Point2D(1.6701053360518172,0.32827580405235723),new Point2D(1.082532657165029,1.3710407825801023),new Point2D(1.675282205479384,1.750515733468983),new Point2D(1.0727819883740777,3.5514454084611167),new Point2D(1.2604634865466067,2.3040486172126657),new Point2D(1.4333393829156307,3.573020851830877),new Point2D(1.5339134005555302,3.825745769242186),new Point2D(1.4206146979787495,0.6931202009811144),new Point2D(1.0308011096068195,1.3340498272484278),new Point2D(1.6064769066386584,3.3373338756406046),new Point2D(1.0362225828050131,2.442308945779804),new Point2D(1.633435278400147,3.332281366102604),new Point2D(1.2188304240292132,1.8524326757864311),new Point2D(1.2042567461034464,1.226538065529983),new Point2D(1.4852277951449298,1.194495945494889),new Point2D(1.3411756321838406,1.4653215018355703),new Point2D(1.2152828553801496,1.4779139961478482),new Point2D(1.1998517023853168,1.1845628997617896),new Point2D(1.28851966447683,0.4198160286249024),new Point2D(1.9732331595037658,1.389987634499041),new Point2D(1.561742246012371,3.916240086653355),new Point2D(1.6704806570187511,2.286831490943552),new Point2D(1.7080476318423945,1.9217753531021975),new Point2D(1.8061085652097444,1.5258157832946688),new Point2D(1.5773843828937069,0.0032774547072413895),new Point2D(1.2844997260780358,3.131007623243535),new Point2D(1.488812757436068,2.833279941686081),new Point2D(1.0149501159347385,1.8751903286040923),new Point2D(1.114622776192475,2.8887288786953516),new Point2D(1.0423994657115745,0.8683678466502363),new Point2D(1.8664664820764592,0.6206642798330884),new Point2D(1.2290040715903503,1.1250291674866952),new Point2D(1.9436606066632,3.360473923429502),new Point2D(1.7717084495048354,2.8757982408452),new Point2D(1.453815829424245,3.2283444416195413),new Point2D(1.0909713583057976,3.3890926000175314),new Point2D(1.2659490131959956,3.3095348330103747),new Point2D(1.8758031953201928,1.0684171897936512),new Point2D(1.506234668966969,2.3640967271930324),new Point2D(1.3202705051621044,0.5909181816395876),new Point2D(1.8565516087709062,1.335667592492626),new Point2D(1.8366240765352082,2.8528214550416195),new Point2D(1.0659814793549893,3.9249607090669048),new Point2D(1.7802068670732496,0.48738037923981503),new Point2D(1.3354151428608194,0.4935775325034415),new Point2D(1.971297776472904,0.7209590941959134),new Point2D(1.4854231576031616,1.5637674919204292),new Point2D(1.0490480220555618,2.3328750607512525),new Point2D(1.0675466604602377,0.17975992384937545),new Point2D(1.9426657272304761,2.4846263337616743),new Point2D(1.8291650902250884,3.3962932753116553),new Point2D(1.9950055291498807,2.18412902689749),new Point2D(1.1920965989463643,2.159966426378818),new Point2D(1.5237190171941917,0.150589260604856),new Point2D(1.65508768746562,1.8490375934541512),new Point2D(1.8551227564238366,1.8456347340910995),new Point2D(1.2803483666648878,0.7372952761352081),new Point2D(1.1159394170631793,3.4363313155162776),new Point2D(1.6742888218380543,0.6476266194878382),new Point2D(1.3180691590338605,3.455709463427957),new Point2D(1.1226140977666605,3.060907565184455)
        };

        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(test1, test2);
        ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(test1, test2);
        assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
    }
    // Deterministic Recursive case 6 - 2
    @Test
    @Tag("score:0")
    @DisplayName("Recursive case 6.2")
    void recursiveTest100() {
        Point2D[] test1 = new Point2D[]{
              new Point2D(0.3104883784423198, 1.0301640509204901),new Point2D(0.2124256910530795, 1.3065848884190654)
        };

        Point2D[] test2 = new Point2D[]{
                new Point2D(1.6941321354891516, 1.692576718564324),new Point2D(1.2725168758544645, 3.583159731387113)
        };

        ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(test1, test2);
        ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(test1, test2);
        assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
    }
    public static void main(String[] args) {

        int correct_counter = 0;

        while(true) {

            int n = 100;
            // Each point in A will take value in the box [aXMin, aXMax) x [aYMin, aYMax)
            // and each point in B will be in the box [bXMin, bXMax) x [bYMin, bYMax).
            // Feel free to modify the values to change the size and location of the boxes.
            // However, if your box is invalid, this test will fail with a RunTimeException.
            double aXMin = -10;
            double aXMax = 10;
            double aYMin = 10;
            double aYMax = 20;
            double bXMin = 10;
            double bXMax = 20;
            double bYMin = 0;
            double bYMax = 40;

            if (aXMax < aXMin || aYMax < aYMin || bXMax < bXMin || bYMax < bYMin) {
                throw new RuntimeException("Box constraint is not valid");
            }

            Point2D[] aPoints = new Point2D[n];
            Point2D[] bPoints = new Point2D[n];
            for (int i = 0; i < n; i++) {
                double aX = aXMin + Math.random() * (aXMax - aXMin);
                double aY = aYMin + Math.random() * (aYMax - aYMin);
                double bX = bXMin + Math.random() * (bXMax - bXMin);
                double bY = bYMin + Math.random() * (bYMax - bYMin);
                aPoints[i] = new Point2D(aX, aY);
                bPoints[i] = new Point2D(bX, bY);
            }
            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(aPoints, bPoints);

            boolean correct = closestAntennaPair.distance() == closestAntennaPairBruteForce.getClosestDistance();
            if(!correct) {

                String s = "";
                s = s + "A: [";

                for(int i = 0; i<aPoints.length; i++) s = s + ((i==0)?"":", ") + aPoints[i];

                s = s + "]\n";
                s = s + "B: [";

                for(int i = 0; i<bPoints.length; i++) s = s + ((i==0)?"":", ") + bPoints[i];

                s = s + "]\n";
                s = s + "REAL: " + closestAntennaPairBruteForce.getClosestDistance();
                s = s + "\n";
                s = s + "CALC: " + closestAntennaPair.distance();
                System.out.println(s);
                break;
            }else {
                correct_counter++;
                if(correct_counter%1000 ==0) System.out.println("correct: " + correct_counter);
            }


        }



    }
}
