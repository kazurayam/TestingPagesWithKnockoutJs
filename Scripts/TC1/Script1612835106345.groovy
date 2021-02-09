import java.nio.file.Path
import java.nio.file.Paths

import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

Path page = Paths.get(RunConfiguration.getProjectDir()).resolve('page.html')
String url = page.toFile().toURI().toURL().toExternalForm()

WebUI.openBrowser('')
WebUI.setViewPortSize(800, 600)
WebUI.navigateToUrl(url)
WebUI.delay(1)

// TestObject that points the <input id="nm"> field
TestObject nmTObj = new TestObject("nm").addProperty("css", ConditionType.EQUALS, "#nm")

// try to get the prepopulated value in the <input id="nm"> filed
println "nmValue=${WebUI.getAttribute(nmTObj, 'value')}"

// retrieve the prepopulated value in the <input id="nm"> field
WebElement nm = WebUI.executeJavaScript("return document.getElementById('nm');", null)
println "nm.getAttribute('value')=" + nm.getAttribute('value')

// type new value into the field
WebUI.setText(nmTObj, "John Doe")

WebUI.delay(1)

// click the button to send event to the Knowout data-binding framework
TestObject sendTObj = new TestObject("send").addProperty("css", ConditionType.EQUALS, "#send")
WebUI.click(sendTObj)

// retrieve the value again
WebElement nm2 = WebUI.executeJavaScript("return document.getElementById('nm');", null)
println "nm2.getAttribute('value')=" + nm2.getAttribute('value')
println "nmValue=${WebUI.getAttribute(nmTObj, 'value')}"

// verify if the value in the field was successfully updated
assert 'John Doe' == nm2.getAttribute('value') 

WebUI.delay(1)

WebUI.closeBrowser()