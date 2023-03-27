package application.actionlist;

import application.action.Action;

/**
 * An ActionList is an object that implements a end-user menu.<BR>
 * It is defined by a title (printed on top of the menu).<BR>
 * It is also defined by a list of different action objects that the menu manages.<BR>
 * It is attended to :<BR>
 * - display the end-user menu numbered from 1 (list of messages of actions).<BR>
 * - display a quit option (0).<BR>
 * - wait for some user-response.<BR>
 * - launch the requested action.<BR>
 *
 * It is parameterized by the  type of object on which the actions of the list action may act on (execute on).<BR>
 *
 * @param <E> The type of object on which the list action may act on.
 */
public interface ActionList<E> extends Action<E>{
	/**
	 * Title of the list of actions (menu).
	 *
	 * @return the title of the action list
	 */
	public String listTitle();

	/**
	 * The number of actions in the action list.
	 *
	 * @return number of actions in the action list.
	 */
	public int size();

	/**
	 * Add an action at the end of the list action if it does not yet exists.
	 *
	 * @param ac the action to add
	 * @return true if action is added, else false
	 */
	public boolean addAction(Action<E> ac);
}