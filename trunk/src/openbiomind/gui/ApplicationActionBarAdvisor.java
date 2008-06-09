/**
 * ApplicationActionBarAdvisor.java
 *
 * The file ApplicationActionBarAdvisor.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * The class ApplicationActionBarAdvisor.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jun 1, 2008
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

   /** The exit action. */
   private IWorkbenchAction exitAction = null;

   /** The about action. */
   private IWorkbenchAction aboutAction = null;

   /**
    * Instantiates a new application action bar advisor.
    *
    * @param configurer the configurer
    */
   public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
      super(configurer);
   }

   /*
    * @see org.eclipse.ui.application.ActionBarAdvisor#makeActions(org.eclipse.ui.IWorkbenchWindow)
    */
   @Override
   protected void makeActions(final IWorkbenchWindow window) {
      // Exit action
      this.exitAction = ActionFactory.QUIT.create(window);
      register(this.exitAction);

      // About action
      this.aboutAction = ActionFactory.ABOUT.create(window);
      register(this.aboutAction);
   }

   /*
    * @see org.eclipse.ui.application.ActionBarAdvisor#fillMenuBar(org.eclipse.jface.action.IMenuManager)
    */
   @Override
   protected void fillMenuBar(final IMenuManager menuBar) {
      /*
       * File Menu
       * - Exit
       */
      final MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
      fileMenu.add(exitAction); // Exit action
      menuBar.add(fileMenu); // add menu to menu bar

//      /*
//       * Edit Menu
//       */
//      final MenuManager editMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
//      menuBar.add(editMenu); // add menu to menu bar

      /*
       * Set marker for Action Sets to appear here onwards
       */
      menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

//      /*
//       * Window Menu
//       */
//      final MenuManager windowMenu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);
//      menuBar.add(windowMenu); // add menu to menu bar

      /*
       * Help Menu
       * - About
       */
      final MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
      helpMenu.add(aboutAction); // About action
      menuBar.add(helpMenu); // add menu to menu bar
   }

}
