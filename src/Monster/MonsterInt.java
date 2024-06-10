package Monster;

import Entities.Entity;

public interface MonsterInt<T, U> {
    public void setAction();
    public void damage(Entity entity);
    public void draw(T t, U u);
}
