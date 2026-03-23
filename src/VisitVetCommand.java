public class VisitVetCommand implements Commands{
    private Pet pet;
    public VisitVetCommand(Pet pet) {
        this.pet = pet;
    }
    @Override
    public void execute() {}
}
