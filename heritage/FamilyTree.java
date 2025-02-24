package heritage;


public class FamilyTree {
    private Person grandFather;
    private Person grandMother;

    public FamilyTree(String grandFatherName, String grandMotherName) {
        grandFather = new Person(grandFatherName);
        grandMother = new Person(grandMotherName);
        grandFather.addChild(grandMother); // Connect grandMother as a child to show the link
    }

    public Person getGrandFather() {
        return grandFather;
    }

    public Person getGrandMother() {
        return grandMother;
    }
}