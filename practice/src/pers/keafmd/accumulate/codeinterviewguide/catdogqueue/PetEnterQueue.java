package pers.keafmd.accumulate.codeinterviewguide.catdogqueue;

/**
 * Keafmd
 *
 * @ClassName: PetEnterQueue
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:33
 */
public class PetEnterQueue {

    private Pet pet;
    private long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
