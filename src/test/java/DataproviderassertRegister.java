import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class DataproviderassertRegister {

        public static WebDriver driver;

        @DataProvider(name = "RegistrationTesProvider")

        public static Object[][] getlogData() {
            Object[][] data = new Object[1][8];
            data[0][0] ="";
            data[0][1] ="";
            data[0][2] ="";
            data[0][3] ="" ;
            data[0][4] ="" ;
            data[0][5] ="";
            data[0][6] ="" ;
            data[0][7] ="" ;
            //data[0][8] =
            return data;
        }

    }


