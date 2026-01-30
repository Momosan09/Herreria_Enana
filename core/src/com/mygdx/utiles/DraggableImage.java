package com.mygdx.utiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class DraggableImage extends Image {
    private DragAndDrop dragAndDrop;
    private Target target;

    public DraggableImage(Texture texture, DragAndDrop dragAndDrop) {
        super(texture);
        this.dragAndDrop = dragAndDrop;

        // Habilita que la imagen sea arrastrable
        setTouchable(Touchable.enabled);

        // Crea una fuente de arrastre para esta imagen
        dragAndDrop.addSource(new Source(this) {
            public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                // Inicia el arrastre
                Payload payload = new Payload();
                payload.setObject(this);
                payload.setDragActor(getActor());

                return payload;
            }
            public void dragStop(InputEvent event, float x, float y, int pointer, Target target) {
                // Limpia el estado del arrastre
            }
        });
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
