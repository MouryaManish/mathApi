import com.sojern.CompareVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class CompareVersionTest {
    private static final Logger logger = LogManager.getLogger();
    List<String> list1 = Arrays.asList("0.1","0.1.1","0","1.2.3","1.2");
    List<String> list2 = Arrays.asList("0.1.1","0.1","0","1.2.2.2.2.3","1.2.0.0");
    List<Integer> resultLists = Arrays.asList(-1,1,0,1,0);

    @Test
    public void testCompareVersion(){
        for(int i=0;i<list1.size();i++){
            logger.debug("argument_a: {}, argument_b: {}",list1.get(i),list2.get(i));
            CompareVersion compareVersion = new CompareVersion();
            int result = compareVersion.compare(list1.get(i),list2.get(i));
            assertThat(result).as("Wrong output").isEqualTo(resultLists.get(i));
        }
    }
}
