public class ShoeDisplayDriver {


        public static void main(String[] args) {

            Shoes a = new Shoes(9, Shoes.TENNIS);
            Shoes b = new Shoes(10, Shoes.TENNIS);
            Shoes c = new Shoes(11, Shoes.TENNIS);
            Shoes d = new Shoes(9, Shoes.DRESS);
            Shoes e = new Shoes(11, Shoes.DRESS);

            // TODO: instantiate a "shoes display" list

            ShoesDisplay shoeDisplay = new ShoesDisplay();

            // TODO: add all shoes into the "shoes display" list

            shoeDisplay.add(a);
            shoeDisplay.add(b);
            shoeDisplay.add(c);
            shoeDisplay.add(d);
            shoeDisplay.add(e);

            // TODO: print the "shoes display" list
            System.out.println(shoeDisplay);
            // TODO: print how many shoes are in the tennis shelf displaying "#tennis shoes = ..."
            System.out.println("#tennis shoes = " + shoeDisplay.total(Shoes.TENNIS));


            // TODO: print how many shoes are in the dress shelf displaying "#dress shoes = ..."
            System.out.println("#dress shoes = " + shoeDisplay.total(Shoes.DRESS));


        }
    }

