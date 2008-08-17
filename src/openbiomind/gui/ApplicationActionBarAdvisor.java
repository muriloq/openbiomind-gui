/**
 * ApplicationActionBarAdvisor.java
 *
 * The file ApplicationActionBarAdvisor.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * The class ApplicationActionBarAdvisor. To add menus through programming override the protected method fillMenuBar()
 * of {@link ActionBarAdvisor} as shown below: <br /> <code>
 * protected void fillMenuBar(final IMenuManager menuBar) {<br />
 * &nbsp;&nbsp; // Menu that must appear before the action sets must be added here<br />
 * <br />
 * &nbsp;&nbsp; // Set marker for Action Sets to appear here onwards<br />
 * &nbsp;&nbsp; menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));<br />
 * <br />
 * &nbsp;&nbsp; // Menu that must appear after the action sets must be added here<br />
 * }</code>
 * 
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Aug 16, 2008
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

   /**
    * Instantiates a new application action bar advisor.
    * 
    * @param configurer the configurer
    */
   public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
      super(configurer);
   }

}
