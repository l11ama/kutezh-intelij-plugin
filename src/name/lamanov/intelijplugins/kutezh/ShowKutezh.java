package name.lamanov.intelijplugins.kutezh;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;

import javax.swing.*;
import java.awt.*;

public class ShowKutezh extends AnAction {

    private static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
    private static final Icon SMALL_ICON = new ImageIcon(ShowKutezh.class.getResource("Kutezh.png"));

    public void actionPerformed(AnActionEvent e) {
        IdeFrame ideFrame = WindowManager.getInstance().getIdeFrame(DataKeys.PROJECT.getData(e.getDataContext()));
        perform(ideFrame);
    }

    public static void perform(IdeFrame ideFrame) {
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder("", SMALL_ICON, TRANSPARENT_COLOR, null)
                .setBorderColor(TRANSPARENT_COLOR)
                .setFadeoutTime(5000)
                .createBalloon()
                .show(RelativePoint.getSouthEastOf(ideFrame.getComponent()), Balloon.Position.atRight);
    }
}
