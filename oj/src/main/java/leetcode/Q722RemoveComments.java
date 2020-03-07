package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 1. 第一个有效注释优先于其他注释
 * 2. 一行删除注释后为空则不要输出该行
 * 3.
 */
public class Q722RemoveComments {

    public List<String> removeComments(String[] source) {
        StringBuilder sb = new StringBuilder();
        for (String line : source) {
            sb.append(line).append("\n");
        }
        int i = 0;
        while (i<sb.length()-1) {
            char first = sb.charAt(i);
            char second = sb.charAt(i+1);
            if (first=='/'&&second=='/') {
                int startIdx = i;
                while (sb.charAt(i) != '\n') {
                    i++;
                }
                sb.delete(startIdx, i);
                i = startIdx - 1;
            } else if (first=='/'&&second=='*') {
                int startIdx = i;
                i = i+2;
                while (!(i<sb.length()-1&& sb.charAt(i)=='*' && sb.charAt(i+1)=='/')) {
                    i++;
                }
                sb.delete(startIdx, i+2);
                i = startIdx-1;
            }
            i++;
        }
        List<String> res = new ArrayList<>();
        for (String item : sb.toString().split("\n")) {
            if (!item.isEmpty()) {
                res.add(item);
            }
        }
        return res;
    }

    @Test
    public void testCase00() {
        String input = "/* This is a test\n" +
                "   multiline  \n" +
                "   comment for \n" +
                "   testing */";
        Pattern p2 = Pattern.compile("/\\*.*\\*/", Pattern.DOTALL);
        String s = Pattern.compile("/\\*.*\\*/", Pattern.DOTALL).matcher(input).replaceAll("");
        System.out.println(s);
    }

    @Test
    public void testCase01() {
        String input = "  /* variable declaration */";
        System.out.println(input.replaceAll("/\\*.*\\*/", ""));
        System.out.println(input.contains("/*"));
    }

    @Test
    public void testCase02() {
        String[] input = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        for (String line : input) {
            System.out.println(line);
        }
        System.out.println("========================================");
        List<String> res = removeComments(input);
        res.forEach(System.out::println);
    }

    @Test
    public void testCase03() {
        String[] input = {"a/*comment", "line", "more_comment*/b"};
        List<String> res = removeComments(input);
        res.forEach(System.out::println);
    }

    @Test
    public void testCase04() {
        String[] input = {"//*cdacbbad/*/ccae//*d*//ebaec*////*/*/d*//de//*a//*c/*//*/c/*/ec/*/dbdd//*add","/*/b/*/ab//*/*/da//*daed//*//*eebec//*/*/c/*/bea/*/cdb*///*/adadb/*/*//*////*c","db//*//*/*/ad//*c/*/c/*//*/edda*//eeed/*/e/*/bdabbbdede*//bb*//ed//*abc*//ddcc","bb//**///*/ea/*/c/*/ebc//*ee/*/*//be/*///*/*/ada/*/a*//b//*a*///*/e/*/ddda//*//*","cadadca*///*/b/*/bac/*/d*//c//**//de//*//*/*///**//baaceccd*//*//ce/*/eaceee/*/","/*/bc/*/aa/*/a//*a*//*//a/*/ded/*/*//b//*/*/bad//**//d//*/*///**//bc//*/*///**//","d*//b/*/bc/*///*//*/*/ecac//*aded*//e/*//*/eb*///*//*/c//*d//**//ab/*/*///*/eb","c*////*/*///*/*/c/*/dd//*aa*//d/*/*//aac*//ce*//d/*/de*//dbee/*/*//e/*/eacdabd","e/*///*ada//*ece*//ae/*/ea/*/aaeca//*e//*ad/*///*//**////*a*//d/*/b/*/deab*//a","c/*//*/d/*//*///*/*//*/*//*//*//ae/*/e//*eebe//*d//*/*//*//*/aca//*/*/*//c*//*//","cb*///*/ee*//*//*//cdde*////*//**//dedd/*//*///*/*/aa/*/*//bee//*/*/*//aa/*/ac","//*/*/eabcbdc/*///*/*///**//cebc/*/edab*//bec/*//*//*/eeded//*ee//*ebba*//bbc/*/","//*ebc/*/b*///*/d*//b/*/*//e*///*/c/*/da/*///*/*///*dceb/*/aea//**///*///*/*///*","adcbcdaa/*///*b*//cd//*daed*//ce//*d/*/eda//*d/*/e*//b//*eeb//*dbd*//c*///*/c*//","eca/*/*////*ddb*//*///*/dcc//**//*//aa//*c/*/c//*b*///*/*//bebcbeb/*/b*//aabe/*/","/*//*///*bd//*abedd//*ceabd//*d*//*//cdc/*/cbcbedeec*///*/cebcbb//*//*bbecb//*","cbc/*/abbedaab//**//be//*a//*dddaaa/*/d//*ac*//c/*/eeceba*///*/d//*a/*/*///*/d","*////*badbe*////**///*/dd/*/c*//c/*/bd*///*/c*///*/*//a/*/eadde/*/*////*aaece/*/","ececcbaca/*/bacb*//bdab*//aeea//*b//*e*//d//*debbabea/*/dc/*/b*//da*//*//acb//*","*//ed//**///*///*de*//*//b//*dab//*eabeda/*//*/dcac/*/*//ba/*/e*//dc//*/*///*e","//*b*//dead/*/cebc//*/*/de/*/c/*/ad/*/eac//*ac/*/ddcb*//ac/*//*/ccddebdac*///*/","e/*/*//b/*/ee/*/acbd//*cccadbcb*//eee//*cadab/*/db//*//*bc//*cc*//bdd//*//**//","b//**//bcdc*//e/*/bd*//e*///*/de*//d/*/*//b/*/e*//*//a*//abbb*//d//*db//*dc//*","/*/d/*/e//*/*/bcd*////*ca*//d*//*//dd/*/acabbcbdb/*//*//*///*bee//*a*//*//b*//","/*/a*//dab//*c//*c//*a/*/d/*///**///*///*ebd*//*//ccc/*///*dbc/*/e/*/*//b/*//*/","*//daecab/*/aeb/*//*///*/*//*///*//*bc*///*/caccaeaab//*eaeaaebe/*///*dcad//*e","ae/*/ab/*/e*//be/*/*//d//*b*//e/*/da//*cdddddece*//a/*/e//*ee//*/*/dbea*//c*//","d/*/dc//*ebd/*/c/*///*db//*badbeecd*//*///*/edde//*bb/*/c/*/b*//ababd/*/dbd//*","aeddda*////*db//*ecdb*//*////*//*e*//dc/*/dca/*/e/*///*/*/edb*//a//*a*////*//*","//*eaaddccaeaec/*//*/d/*/ee/*/db*//a//**//eab*//daa/*//*/*////**////*/*/bcadcd","//**//d//*cdaab//*ed/*///*caed//*ecbbce*///*/aba/*/e/*/be/*//*///*//*c/*///*a*//","a*//a/*///*/*/a/*//*/ed/*/bbdadea*//e*//ee/*///*abdeb*//e*//*//a//*ebbddddc//*","a/*/de/*/dac*//c*////*ac*////*//*bae//*/*//*/cdc*////*//*bcedcbadbd/*/bba//*e//*","*//ce//*/*/dcc*//ee*//a*//c//**//adeaac//*bedd//**//decee*//*//dcee//*abe//*/*/","ecbd*//*////*c*//dec//*acc/*/ad//*a*///*/e/*/cabde*////*e//**//abe*//ba/*/b*//","*//ccbd*///*/ed/*/b*//cacadddd/*/bbbbeee*//acbaeca*///*//*/ab//*c*//cbcd//**//","b//*//*be*////*c*//dad//*d//*bd//*bd*//ebad*//ec/*//*//*//*///*d/*///*eb*////*","b//*cee//*//*db//*d/*/*//*//cebc//*cae//*ca/*/cebb*//*//cbeb//*bebdeadb*////*/*/","da//*eeaadbdc//*c*//aee/*/dee//*//*ed*////*acd*//*///*/c//*b*//*///*///*a//*b//*","e/*/*//cac*////*dd/*/*//a//**//eadb//*d*//*//c//*aded//**//*//bde//*/*/da/*/d//*","*//c*//db//*//**////*bedc/*/b//*b/*/c//*//*//*c/*/d/*/cec*////**///*/a/*/e*////*","ea/*/a/*/a//**//b/*/cbae//*e/*/bcdb*//b/*//*/e//**///*/e//*eedac//*cdea//*c/*/","//*c*//e//*ed//*b//*ede*///*/bced/*/ecebcadcbd/*/*//be/*///*e/*///*//*b/*///**//","ea/*/cd/*/*////*d/*//*/bbad*//*////**//acbbabbdd*//aec//*b/*/b/*/bdecd*//c*//c","c//**//dcc*////*//*/*/d*//da*////*bbad//*/*//*/b*//eeea*//*////*e//*b/*/*///*/","bad/*/cdc*//e//*e/*/ddb*//ded//*ede*//eaeb*//d*//*//e/*/e*///*/ccedaad*//dab*//","aeea//*/*/*////*//*d*//bdcbb/*///*ddaa//*bbdd/*/*//bea//**//bbae/*/bed//**//cc","/*/d//*cb//*cb/*/*//cc//*ecd/*/ba/*/ddc*//d//*cbedbe*//*//bdae//*c*//c/*/ddbb*//","be//*ec*//*//ee//*//*eec//*d//*ac/*/*///*//*/*//caeaa/*///**//c//*aecddbbbdcd//*","//*ee//**//ba*//ed//*a/*//*/daba/*/ddc*//e*//eac//*//**//c/*/dadeebee*////*add","/*//*//*/b//*a//*d*//a*//*//*//ed//*b//**//e//*/*/e//**//b//*aac*//eacdcecd//*","aabeeac/*///**//aae*//*//ba/*/abea*//bdade*///*/eb/*/dde*//b//*/*/e/*//*/a//*//*","/*/aacbc*////*bac//*b//**//*//edde*//bbdecc//*acbbbcb//*decdcde*//ddad//*/*/aa","//*//*bcdd//*/*//*/c/*/*//dd//*c/*/acddedaac*//caed*//*//*//dd//**//*//c*///*/","//**///*/e//*//*bbdbdaa*////*b//*d//**//e//*cddccaee*//*//a*//ee*////*/*/e/*/b","db/*/dc*////*b/*/abbea*//e*//ce//*e/*//*/aad*//ae*//ad//*ebbcda*//c/*/daccb*//","//*d/*//*/b*//ed//*//**///*/d*//a*//*//baebdeced/*//*/*//dddc*//eadaebabbea*//","b/*/ee//**//ececc//*c/*/cbcbbd/*///*aaed/*/abac//*ee//**////*acebbbb//*/*//*/e","ca/*/eddd/*/aebed*//ddccd//*ecbb//*//*/*/d/*/*//dcb*//acda//*eedeaab//*d*///*/","//*c//*b/*/dcb/*/a*////*/*/*//d*//*//a//*beda//*bb//*//*aebbddaa//*ad*///*///*","/*/dde*//beee//*aaba*////*d*//*//da//*b//*/*/*//abdbe/*//*/ba//*//**//cb//*e/*/","bce//*bce/*///*ea//*deb/*//*/d//*c/*/cdd//*a/*/d/*/*//*//ebcbabe//**////**///*/","*//d/*/adcbbec*//dbaeeec/*/cb/*/dbc/*/eeedd*////*a/*/dbba*///*/c*////*e*///*///*","/*/c/*/*///*/ebaaee*//ea//*/*//*/e*//dbaa/*/c//**//aaadccaaaae*//*//debc//*bb*//","c//*//*cb*//a/*/bbaddb/*/eae//*dba*//c/*/b/*///*cae//*//*b//*bcceba//*cdee*//d","dd//*be/*/c*//dccbbbc/*/eedabee*//a/*//*/edc*//eddab/*/aababb//*/*/ae*//d*////*","a/*/aa*////**//*//e//*ab*//ab*//ebc//*aee//*cd/*/dbbebce*///*/b/*/*////*bdaaac","//*//*e//*/*/*//e*//aaaaae*//c//*de/*/*////*cc*//ebbdeedcd//*d//*cdcbc//*a/*///*","e/*/b/*/b*//cea*//ca/*/*//a/*///*bee//*edcd*////*d//*dddcabaae*//*//acc/*//*/e","*////*ceead//*d//**//dcaee*//adc/*//*//*/eb/*/de//*//*e/*/dea*//*///*/cd/*/a/*/","dace*//de//*/*/d//*dd//*a/*/ab//*dc*//d*///*///*cacd/*/*//*//bd/*///*cdeddbeb*//","//*e/*//*/b//*/*/a//*daec/*/e//*c/*/ebdeb/*/a//*dbbcadaaebea*//cdc//*//*/*/b//*","/*//*/edaeeb//*c*//bcbad*//eda*////**//ccc*//cac/*/da/*/d*//e//**////**//ebed//*","aac*////*ce*//*///*/ce/*/eccde//*ed//*//*a//*cc*//c/*/b//**////*/*/b//*/*/*//b","ccdce*//ce/*/da/*/a*////*cad*///*///*dedd//*aaca/*/c/*/bdc//*dba//*ebd*///*/*//","ccc//*a//*aaedaddc*//*//b/*//*/bc//*/*/cebccbe//*ad*//cceacc/*//*/eba/*/ad*//c","b//*ae/*/a*//d/*/adadde*//*//dee*////*//*/*///*bb/*/addcd//*daac/*/e//**//ca/*/","//*/*//*/ac//*/*/*////*bc//*adae*//d*////*cecdbdcaeabbea//*//*b*//bba/*/b/*/cd","*//eebb//*aeceebe*///*/ca/*/*////*/*/eccce/*/d//*d//*bb/*//*/ecb/*/d/*/de//*c//*","*////*//*/*/b*////*dd/*/cb//*/*//*/c*//b*//*///*/aee//*d/*///**//*//e/*/cebe*//","//*//*/*/c//*e/*/daadd*//*///*/e*//cda/*///*ba//*//*beb/*/aad*//de/*/c*//e*//c","aecaacabe//*bdebc*//da//*b//*//*/*//*/eebdbbeca*//b/*/*//e*//ec/*///*c//*//**//","/*/cec*//d*//ca/*/d*///*/c*//*//ba*//c/*/b//*/*/bb//*bc/*/be/*//*/a//*ce/*//*/","ddbe//*e/*///*aebc*//a//*//*e//*bc/*/dac//*/*///*/*/cdcdbc//*dbd//*ca/*/ea/*/b","//*/*//*/c/*/b/*///*cdcebe//*/*/db//**//d*//b/*/*//bbd/*/d/*//*/c*//cba//*cecd","c*//bad//*e/*/cacd//*a*////*e/*/ee*//aad/*/e*//b*////*eb//*/*//*/d*///*///*//*","c*//c*//aa//*dd//**////*a*//daacdbad/*/d*//be/*//*/a/*///**//aae//*bc*//ee/*/e","/*/*///*/c/*/dabe/*//*/da//**//e/*/e/*/a/*/eada/*//*/a/*/d*///*/ad//*deeadb*//","c//*/*/bdc/*/b/*/b/*/badcabad*///*/c//**//a/*/aaad*//a*//eaeee*//db*///*/d*///*/","c/*///*/*/ebbbbc*//c*//c*//dae/*/acbe*////*b*///*/a/*/d//*e*//ad//*cead//*bbcb","//*e/*//*/*//ecddaa/*/cadccc*//ebcad/*/*////*eeb*//aa/*/de/*/dcdaa*//*//*//d*//","dc//*ebca*//aca//*//*e/*/*//abdbc*//*//e//*/*//*/dedcdabd/*/bae/*/a//**//ed*//","//*b*//a/*//*/aed//**//aceced/*//*/e//*ecada*////*//*a*//*//eedccebc/*/*///*/d","//*bcee*//bbbaecede*//cea//*c/*/d*//c*//cabadab/*/b//*cdaaeeb/*/e*//cb/*/cbd/*/","*//a/*///*aeeb/*//*/bbac/*/*//ebcadc//*/*//*/ee//*/*/e/*/ab//*/*///*a//*//**//","*//ceb//*cd//*a/*///*/*/c*//bcbcc//*c*///*/*//ccabbd/*/c*////*//*eda*////*//**//","c*////*bbaedbdce/*/*//*//e*//*//ddc/*/deae/*/a/*//*//*//*/e*///*/c*///*/ebc/*/","/*/d//**//a*//aeec//*dc*//a/*/*//d/*///*c/*/cbe//*/*/ce*//eddec/*/ececedccc*//","*//caad//*d*///*/b//*e//*bb//*a*//aad*//b/*/eb*//baeced*////*//**//ceb/*/a/*/*//","ba*//a//**//bc//*/*/c/*///*cc//*b//*/*/cd//*baece*//c/*/bb//*aa*//baaade*//d*//"};
        List<String> res = removeComments(input);
        res.forEach(System.out::println);
    }
}
