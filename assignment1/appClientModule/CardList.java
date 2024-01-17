

public class CardList {
	/* Attributes: first, keeps the front of the list
	 * count, keeps track of the number of Cards on the list
	 */
	private Card first;
	private int count;

	public int size() {
		return count;
	}
	
	public Card getFirst() {
		return this.first;
	}
	
	/* 
	 * Constructor (I did this one for you)
	 * if all is true, it creates a complete deck of 40 cards,
	 *                 4 colors, numbers from 0-9
	 * if all is false, it just creates an empty list
	 */
	public CardList(boolean all) {
		first=null;
		count=0;
		if (all) {
			for (Card.Colors color : Card.Colors.values()) {
				for (int number=0;number<=9;number++) {
					add(number,color);
				}	
			}
		}
	}

	/* DO THIS:
	 * traverse: is not actually used in the gui version of the program, 
	 * but it is useful while debugging the program
	 */
	public void traverse() {
		Card current = this.first;
		while(current != null) {
			System.out.println(current.toString());
			current = current.getNext();
		}
	}

	/* DO THIS:
	 * add: adds a card to the front of the list
	 * given number and color
	 */
	private void add(int number,Card.Colors color) {
	//	if(color != null && number >= 0 && number <=9){
		this.first = new Card(number, color, first);
		count++;
	//	}
	}

	/* DO THIS:
	 * add: adds a card to the front of the list
	 * given a reference to the new card
	 */
	private void add(Card card) {
		
	//	if(card != null) {
			this.first = new Card(card.getNumber(), card.getColor(), first);
			count++;
	//	}
		
		
	}
	
	/* DO THIS:
	 * countCards: Traverse the list and return the number of cards.
	 *     When complex operations are done on a list, such as
	 *     concatenation of lists, countCards is used to make sure that the
	 *     number of cards is kept updated (just there because we are lazy
	 *     and do not want to think about how to compute the new number
	 *     of cards based on the original ones).
	 */
	private int countCards() {
		int c = 0;
		Card current = this.first;
		while(current != null) {
			c++;
			current = current.getNext();
		}
		return c;
		//return this.count; // you must remove this instruction
	}

	/* DO THIS:
	 * Append(add) a new list of cards "list" at the end of the current list (this)
	 * Notice that it might be possible for this.first to be null
	 */
	public void concatenateWith(CardList list) {
		Card current = list.getFirst();
		Card e = null;
		if(this.first == null) {
			this.first = current;
			while(current != null) {
				current = current.getNext();
				e.setNext(current);
				e = e.getNext();
				
			}
		}
		else {
			Card FL = this.getFirst();
			while(FL != null) {
				e = FL;
				FL = FL.getNext();
			}
		//Card current = list.getFirst();
			while(current != null) {
				e.setNext(current);
				current = current.getNext(); 
				e = e.getNext();
			}	
		}
		
	}

	/* DO THIS:
	 * moveTo: move the front card from this to the front of destination
	 */
	public void moveTo(CardList destination) {
	/*	Card f = this.first;
		this.first = this.first.getNext();//added
		Card d = destination.getCard(first);
		if(d != null) {
			destination.add(f);
		}*/
		if(this.first!=null) {
			destination.add(this.first);
			this.first = this.first.getNext();
		}
	}
	
	/* DO THIS:
	 * moveTo: move the first num cards from this to the front of destination,
	 * it can use the (CardList destination) method repeatedly
	 */	
	public void moveTo(int num,CardList destination) {
		while(num != 0) {
			moveTo(destination);
			num--;
		}
	}

	/* DO THIS:
	 * moveTo: Given a Card x, it finds the card on this list and
	 *         moves it to the front of the destination list.
	 * 
	 */
	public boolean moveTo(Card x,CardList destination) {
		Card current = getFirst();
		Card prev = null;
		while(current != null) {
			if(current.equals(x)) {
				destination.add(current);
				if(prev != null) {
					prev.setNext(current.getNext());
				}
				if(prev == null) {
					this.first = this.first.getNext();
				}
				return true;
			}
			prev = current;
			current = current.getNext();
		}
		return false; // you must remove this instruction
	}
		
	/* DO THIS:
	 * shuffle: Easy way is to create two new empty lists,
	 *          repeat split number of times: move the
	 *          first card of this to the first list, and then 
	 *          the next one to the second list,
	 *          finally, concatenate the two lists to this. 
	 */
	public void shuffle(int split) {
		CardList list1 = new CardList(false);
		CardList list2 = new CardList(false);
		if(this.first != null) {
			for(int i = 0; i < split; i++) {
				list1.add(this.first);
				this.first = this.first.getNext();
				list2.add(this.first);
				this.first = this.first.getNext();			}
		}
		
		this.concatenateWith(list1);
		this.concatenateWith(list2);
	}
	
	/* DO THIS:
	 * search: return a card that matches either the number or color
	 *         of the given card x.
	 *         You must use the matches(false,x) method that you wrote for the
	 *         Card class.
	 */
	public Card search(Card x) {
		Card current = this.first;
		
			while(current != null) {
				if(current.matches(false, x)) {
					return current;
				}
				current = current.getNext();
			}
		
		return current; // you must remove this instruction
	}

	
	/* DO THIS:
	 * getCard: returns a Card in this list that matches exactly
	 *          (use matches(true,card) method in Card) the given card
	 */
	public Card getCard(Card card) {
		Card current = this.first;
		
			while(current != null) {
				if(current.matches(true, card)) {
					return current;
				}
				current = current.getNext();
			}
		
		return current; // you must remove this instruction
	}

}
