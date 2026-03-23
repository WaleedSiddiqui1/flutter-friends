public class UseItemCommand implements Commands{
    private Item item;
    private Pet pet;
    public UseItemCommand(Item item, Pet pet) {
        this.item = item;
        this.pet = pet;
    }
    @Override

    public void execute() {}

}
