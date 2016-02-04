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

public class ShowNichosi extends AnAction {

    private static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
    private static final Icon BIG_ICON = new ImageIcon(ShowNichosi.class.getResource("Nichosi512px.png"));
    private static final Icon SMALL_ICON = new ImageIcon(ShowNichosi.class.getResource("Nichosi256px.png"));

    public void actionPerformed(AnActionEvent e) {
        IdeFrame ideFrame = WindowManager.getInstance().getIdeFrame(DataKeys.PROJECT.getData(e.getDataContext()));
        Dimension ideFrameSize = ideFrame.getComponent().getSize();
        boolean useBigIcon = 1000 <= ideFrameSize.getWidth() || 800 <= ideFrameSize.getHeight();
        Icon icon = useBigIcon ? BIG_ICON : SMALL_ICON;

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder("", icon, TRANSPARENT_COLOR, null)
                .setBorderColor(TRANSPARENT_COLOR)
                .setFadeoutTime(5000)
                .createBalloon()
                .show(RelativePoint.getSouthEastOf(ideFrame.getComponent()), Balloon.Position.atRight);
    }
}
