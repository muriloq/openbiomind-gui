/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (GraphFeaturesWizard.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.GraphFeaturesTaskData;
import openbiomind.gui.main.GraphvizHelper;

/**
 * The class GraphFeaturesWizard.
 * 
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 18, 2008
 */
public class GraphFeaturesWizard extends AbstractTaskWizard {

   /** The graph features wizard page. */
   private final GraphFeaturesWizardPage GRAPH_FEATURES_WIZ_PAGE = new GraphFeaturesWizardPage();

   /** The graph features task data. */
   private GraphFeaturesTaskData graphFeaturesTaskData = null;

   /** The Graphviz helper. */
   private GraphvizHelper graphvizHelper = null;

   /**
    * Instantiates a new graph features wizard.
    */
   public GraphFeaturesWizard() {
      super(Messages.GraFeatureWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.GRAPH_FEATURES_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getGraphFeaturesTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.GRAPH_FEATURES_WIZ_PAGE;
   }

   /**
    * Gets the graph features task data.
    * 
    * @return the graph features task data
    */
   private GraphFeaturesTaskData getGraphFeaturesTaskData() {
      if (this.graphFeaturesTaskData == null) {
         this.graphFeaturesTaskData = this.GRAPH_FEATURES_WIZ_PAGE.prepareTaskData();
      }
      return this.graphFeaturesTaskData;
   }

   /**
    * Gets the graphviz helper.
    * 
    * @return the graphviz helper
    */
   private GraphvizHelper getGraphvizHelper() {
      if (this.graphvizHelper == null) {
         this.graphvizHelper = new GraphvizHelper(this.GRAPH_FEATURES_WIZ_PAGE.getOutputFile());
      }
      return this.graphvizHelper;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getPostSuccessfulExecutionProcess()
    */
   @Override
   protected Process getPostSuccessfulExecutionProcess() {
      final Process dotProcess = getGraphvizHelper().createDotProcess();
      if (dotProcess != null) {
         getGraphFeaturesTaskData().setGraphImagePath(getGraphvizHelper().getOutputFilePath());
         return dotProcess;
      } else {
         return super.getPostSuccessfulExecutionProcess();
      }
   }

}
