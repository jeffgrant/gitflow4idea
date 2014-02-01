package gitflow;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import gitflow.ui.GitflowOptionsForm;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Andreas Vogler (Andreas.Vogler@geneon.de)
 * @author Opher Vishnia (opherv@gmail.com)
 */

public class GitflowConfigurable implements Configurable {
    public static final String GITFLOW_PUSH_ON_FINISH_RELEASE = "Gitflow.pushOnFinishRelease";
    public static final String GITFLOW_PUSH_ON_FINISH_HOTFIX = "Gitflow.pushOnFinishHotfix";
    public static final String GITFLOW_DONT_TAG_RELEASE = "Gitflow.dontTagRelease";
    public static final String GITFLOW_USE_CUSTOM_TAG_COMMIT_MESSAGE = "Gitflow.useCustomTagCommitMessage";
    public static final String GITFLOW_CUSTOM_TAG_COMMIT_MESSAGE = "Gitflow.customTagCommitMessage";
    Project project;

    GitflowOptionsForm gitflowOptionsForm;


    public GitflowConfigurable(Project project)
    {
        this.project = project;
    }

    @Override
    public String getDisplayName() {
        return "Gitflow";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        gitflowOptionsForm = new GitflowOptionsForm();
        return gitflowOptionsForm.getContentPane();
    }

    @Override
    public boolean isModified() {
        return PropertiesComponent.getInstance(project).getBoolean(GITFLOW_PUSH_ON_FINISH_RELEASE, false) != gitflowOptionsForm.isPushOnFinishRelease() ||
               PropertiesComponent.getInstance(project).getBoolean(GITFLOW_PUSH_ON_FINISH_HOTFIX, false) != gitflowOptionsForm.isPushOnFinishHotfix() ||
               PropertiesComponent.getInstance(project).getBoolean(GITFLOW_DONT_TAG_RELEASE, false) != gitflowOptionsForm.isDontTagRelease() ||
               PropertiesComponent.getInstance(project).getBoolean(GITFLOW_USE_CUSTOM_TAG_COMMIT_MESSAGE, false) != gitflowOptionsForm.isDontTagRelease() ||
               PropertiesComponent.getInstance(project).getBoolean(GITFLOW_CUSTOM_TAG_COMMIT_MESSAGE, false) != gitflowOptionsForm.isDontTagRelease()
                ;
    }

    @Override
    public void apply() throws ConfigurationException {
        PropertiesComponent.getInstance(project).setValue(GITFLOW_PUSH_ON_FINISH_RELEASE, Boolean.toString(gitflowOptionsForm.isPushOnFinishRelease()));
        PropertiesComponent.getInstance(project).setValue(GITFLOW_PUSH_ON_FINISH_HOTFIX, Boolean.toString(gitflowOptionsForm.isPushOnFinishHotfix()));
        PropertiesComponent.getInstance(project).setValue(GITFLOW_DONT_TAG_RELEASE, Boolean.toString(gitflowOptionsForm.isDontTagRelease()));
        PropertiesComponent.getInstance(project).setValue(GITFLOW_CUSTOM_TAG_COMMIT_MESSAGE, Boolean.toString(gitflowOptionsForm.isDontTagRelease()));
    }

    @Override
    public void reset() {
        gitflowOptionsForm.setPushOnFinishRelease(PropertiesComponent.getInstance(project).getBoolean(GITFLOW_PUSH_ON_FINISH_RELEASE, false));
        gitflowOptionsForm.setPushOnFinishHotfix(PropertiesComponent.getInstance(project).getBoolean(GITFLOW_PUSH_ON_FINISH_HOTFIX, false));
        gitflowOptionsForm.setDontTagRelease(PropertiesComponent.getInstance(project).getBoolean(GITFLOW_DONT_TAG_RELEASE, false));
    }

    @Override
    public void disposeUIResources() {
        gitflowOptionsForm = null;
    }
}