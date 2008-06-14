/**
 * EnhanceDatasetWizardPage1.java
 *
 * The file EnhanceDatasetWizardPage1.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * The class EnhanceDatasetWizardPage.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 13, 2008
 */
public class EnhanceDatasetWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value =
    * <code>openbiomind.gui.wizards.EnhanceDatasetWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.EnhanceDatasetWizardPage"; //$NON-NLS-1$

   /** The base container. */
   private Composite baseContainer = null;

   /**
    * Instantiates a new enhance dataset wizard page.
    *
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public EnhanceDatasetWizardPage(final String pageTitle, final String pageDescription) {
      super(PAGE_NAME, pageTitle, pageDescription);
   }

   /*
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createControl(final Composite parent) {
      setParent(parent);

      // Required to avoid an error in the system
      setControl(getBaseContainer());
      setPageComplete(true);
   }

   /**
    * Gets the base container.
    *
    * @return the baseContainer
    */
   @Override
   protected Composite getBaseContainer() {
      if (this.baseContainer == null) {
         this.baseContainer = new Composite(getParent(), SWT.NULL);

         // prepare layout
         final GridLayout layout = new GridLayout();
         layout.numColumns = 2;

         // apply layout
         this.baseContainer.setLayout(layout);
      }

      return this.baseContainer;
   }

}
