/*
 * (C) Copyright 2013 Java Test Automation Framework Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.finra.jtaf.ewd.widget.element;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.finra.jtaf.ewd.widget.IElement;
import org.finra.jtaf.ewd.widget.IInteractiveElement;
import org.finra.jtaf.ewd.widget.WidgetException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InteractiveElementBrowserOnlyTest {

    public static String url = "http://localhost:29090/simpleapp/elementstestapp.html";

	public ExtWebDriver wd;
	public static String button = "//button[@id='myButton']";
	public static String invisibleButton = "//button[@id='myInvisibleButton']";

	
	private String getDiv(String id){
		return "//div[@id='" + id + "']";
	}
	
    @Before
    public void setup() throws Exception {
        wd = SessionManager.getInstance().getCurrentSession();
    }

    @After
    public void teardown() {
        wd.close();
        SessionManager.getInstance().removeSession(wd);

    }

    // Selenium issue #3604
    @Test
    public void testDragAndDrop() throws WidgetException{
    	wd.open(url);

        IInteractiveElement ie = new InteractiveElement(getDiv("draggableText"));
        IInteractiveElement destination = new InteractiveElement(getDiv("dropBox"));
        ie.waitForElementPresent();
        destination.waitForElementPresent();
        ie.dragAndDrop(destination);
        destination.waitForText();
        Assert.assertEquals("dragged", destination.getText());
    }
    
    @Test
    public void testDragAndDropByOffset() throws WidgetException{
    	wd.open(url);

        IInteractiveElement ie = new InteractiveElement(getDiv("draggableText"));
        IInteractiveElement destination = new InteractiveElement(getDiv("dropBox"));
        ie.waitForElementPresent();
        destination.waitForElementPresent();
        ie.dragAndDropByOffset(0, -50);       	
        Assert.assertEquals("DragMe", destination.getText());
    }
    
    @Test
    public void testMouseMoveOut() throws WidgetException{
    	wd.open(url);

        IInteractiveElement ie = new InteractiveElement(getDiv("dest"));
        IElement element = new Element(getDiv("content"));
        ie.waitForElementPresent();
        ie.mouseMove();
        ie.mouseMoveOut();
        Assert.assertEquals("Mouse Moved Out", element.getText());
    }
}