package cs355.solution.controller;

import cs355.*;
import cs355.solution.model.Model;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class ICS355Controller implements CS355Controller {

    private final Model model;

    public ICS355Controller(Model model) {
        this.model = model;
    }

    @Override
    public void colorButtonHit(Color c) {
        CS355State.getInst().setColor(c);
        if (CS355State.getInst().getSelectedShape() != null) {
            model.changeShapeColor(CS355State.getInst().getSelectedShape(), c);
        }
    }

    @Override
    public void triangleButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.TRIANGLE);
    }

    @Override
    public void squareButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.SQUARE);
    }

    @Override
    public void rectangleButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.RECTANGLE);
    }

    @Override
    public void circleButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.CIRCLE);
    }

    @Override
    public void ellipseButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.ELLIPSE);
    }

    @Override
    public void lineButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.LINE);
    }

    @Override
    public void selectButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.SELECT);
    }

    @Override
    public void zoomInButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void zoomOutButtonHit() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void hScrollbarChanged(int value) {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void vScrollbarChanged(int value) {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void toggle3DModelDisplay() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void doEdgeDetection() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void doSharpen() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void doMedianBlur() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void doUniformBlur() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void doLoadImage(BufferedImage openImage) {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

    @Override
    public void toggleBackgroundDisplay() {
        CS355State.getInst().setSelectedButtonType(CS355State.SelectedButtonType.NONE);
    }

}
