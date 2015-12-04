package name.lamanov.intelijplugins.kutezh;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollingModel;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;

import javax.swing.*;
import java.awt.*;

public class ShowNichosi extends AnAction {

    private static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
    private static final Icon SMALL_ICON = new ImageIcon(ShowAction.class.getResource("256px.png"));

    public void actionPerformed(AnActionEvent event) {

        Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        CaretModel cm = editor.getCaretModel();
        ScrollingModel sm = editor.getScrollingModel();
        Rectangle r = sm.getVisibleArea();
        LogicalPosition nichosiPosition = cm.getLogicalPosition();
        Point nichosiPoint = editor.logicalPositionToXY(nichosiPosition);
        nichosiPoint.setLocation(nichosiPoint.getX(), nichosiPoint.getY() - r.getY());
        if (nichosiPoint.getY() < SMALL_ICON.getIconHeight()) {
            nichosiPoint.y += SMALL_ICON.getIconHeight() + editor.getLineHeight() * 1.5;
        }


        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder("", SMALL_ICON, TRANSPARENT_COLOR, null)
                .setBorderColor(TRANSPARENT_COLOR)
                .setFadeoutTime(5000)
                .createBalloon()
                .show(RelativePoint.fromScreen(nichosiPoint), Balloon.Position.atRight);
    }
}