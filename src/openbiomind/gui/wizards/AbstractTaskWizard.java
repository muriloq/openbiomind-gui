/**
 * AbstractTaskWizard.java
 *
 * The file AbstractTaskWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * The Class AbstractTaskWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 13, 2008
 */
public abstract class AbstractTaskWizard extends Wizard {

   /**
    * Instantiates a new enhance dataset wizard.
    *
    * @param wizardTitle the wizard title
    */
   protected AbstractTaskWizard(final String wizardTitle) {
      super();
      setWindowTitle(wizardTitle);
      setNeedsProgressMonitor(true);
   }

}
