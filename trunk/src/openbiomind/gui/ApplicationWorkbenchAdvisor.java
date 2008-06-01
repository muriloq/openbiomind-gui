/**
 * ApplicationWorkbenchAdvisor.java
 *
 * The file ApplicationWorkbenchAdvisor.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * The class ApplicationWorkbenchAdvisor.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jun 1, 2008
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

   /** The Constant PERSPECTIVE_ID. */
   private static final String PERSPECTIVE_ID = "OpenBiomind_GUI.perspective";

   /*
    * @see org.eclipse.ui.application.WorkbenchAdvisor#createWorkbenchWindowAdvisor(org.eclipse.ui.application.IWorkbenchWindowConfigurer)
    */
   @Override
   public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
         final IWorkbenchWindowConfigurer configurer) {
      return new ApplicationWorkbenchWindowAdvisor(configurer);
   }

   /*
    * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
    */
   @Override
   public String getInitialWindowPerspectiveId() {
      return PERSPECTIVE_ID;
   }

}
