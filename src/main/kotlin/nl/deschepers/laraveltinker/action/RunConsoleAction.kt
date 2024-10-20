package nl.deschepers.laraveltinker.action

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import nl.deschepers.laraveltinker.Strings
import nl.deschepers.laraveltinker.util.TinkerConsoleUtil
import nl.deschepers.laraveltinker.util.isEnabled

/** Always opens a new tinker console */
class RunConsoleAction : AnAction() {
    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isVisible = true
        e.presentation.isEnabled = false
        e.presentation.text = Strings.get("lt.menu.action.run_console")
        e.project ?: return
        val (enabled, _) = e.isEnabled()
        e.presentation.isEnabled = enabled
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val tinkerConsoleUtil = TinkerConsoleUtil(project)
        val (enabled, file) = e.isEnabled()

        if (enabled && file != null) {
            tinkerConsoleUtil.runTinkerWithFile(file)
        }
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.EDT
    }
}
