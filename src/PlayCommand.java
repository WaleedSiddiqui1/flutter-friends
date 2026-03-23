public class PlayCommand implements Commands{
    private Pet pet;
    public PlayCommand(Pet pet) {
        this.pet = pet;
    }
    @Override
    public void execute() {}
}
