package specs.traits

import geb.navigator.Navigator
import geb.driver.CachingDriverFactory
import geb.waiting.WaitTimeoutException
import java.lang.AssertionError
import com.github.javafaker.*

/**
 * Generic re-usable utility methods.
 */
trait Utils {

  Faker faker = new Faker(new Locale("en-CA"), new Random(Math.abs(new Random().nextInt() % 600) + 1))

  /**
   * Clears the browser and closes it.
   * The next spec to run will open a fresh browser instance.
   */
  void clearAndResetBrowser() {
    resetBrowser()
    CachingDriverFactory.clearCacheAndQuitDriver()
  }

  /**
   * Throw an AssertionError with the given message.
   *
   * @param String the exception message to throw. (optional, default: '')
   * @throws AssertionError
   */
  void fail(String message='') {
    throw new AssertionError(message)
  }

  /**
   * Appends a random 2-3 digit integer to the beginning of the provided string.
   * @param nonUniqueString a string to make unique.
   * @return the given string with random digits appended to the beginning.
   */
  String makeUnique(String nonUniqueString) {
    String random = Math.abs(new Random().nextInt() % 600) + 1
    return random + nonUniqueString
  }

  /**
   * Injects Any JS library into the page under test
   * @param library a string that hold the library url.
   */
  void injectLibrary( library ){
    js.exec("document.body.appendChild(document.createElement('script')).src='$library'")
  }

  /**
   * Injects jQuery library into the page under test
   */
  void injectjQuery(){
    injectLibrary( 'https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js')
  }

  String randomStreetName(){
    return faker.address().buildingNumber() + ' ' + faker.address().streetName()
  }

  String randomCityName(){
    return faker.address().city()
  }

  String randomPostalCode(){
    return faker.address().zipCode()
  }

  String randomProvince(){
    return faker.address().stateAbbr() //'BC'
  }

  String randomFirstName(){
    return faker.name().firstName()
  }

  String randomLastName(){
    return faker.name().lastName()
  }

  String randomPhoneNumber(){
    return '999-999-9999' //faker.phoneNumber().phoneNumber()
  }
  String randomEmail(){
    return faker.internet().safeEmailAddress()
  }

  String randomDescription(){
    String inputString = faker.yoda().quote()
    if (inputString.length() > 255) {
      inputString = inputString.substring(0, 255);
    }
    return inputString
  }

  String randomBusinessName(){
    return (faker.company().name() + ' ' + faker.company().suffix()).toUpperCase()
  }

/*   String getStartDate(){
    date = new Date()
    formattedDate = date.format("yyyy/MM/dd")
    println "Start Date: " + formattedDate
    return formattedDate
  }

  String getEndDate(){
    def date = new Date()
    //date = date + Math.abs(new Random().nextInt() % 600) + 1
    def sdf = new SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(date)
  }
 */
}

