## Detailed Rule Set

The game board has 22 cells arranged in a circular one-way circular path (can't go backward). These cells include 16 properties cells, one **Home**, one **Jail**, one **Car Park**, one **Goto Jail**, one **Chance**, one **Community Chest**. Among these 16 properties cells, four of them are train stations, two of them are utilities, plus the rest of them (10) are ordinary property cells.

Each player has $10000 to begin with. All players start at the position `0`, i.e. **Home**. Worth noting that they would not receive an addition $2000 when they depart from home. $2000 is given to them each time they pass through or arrive Home.

### Property Cells

All property cells are initially not owned by any player. They can be bought by a player if they are not owned. Once a property cell is owned, it cannot be bought anymore. Each property cell has a `baseCost` which tells how much a player needs to pay when he/she buys this property.

When a player moves to a property owned by another player (owner), this player will need to pay a rent to the owner by the formula prescribed later in this section. Their `money` should be changed accordingly.

When a player moves to a property owned by himself/herself, the player will be prompted to build a house **if this is an ordinary property**. There is no upper limit on how many houses a player can build (i.e, if he returns to this property again later, he can build another house). **He cannot build a house on a utility or a train station.** Nothing will happen. Unlike the traditional rule in monopoly, we don't have the *property set* concept which affects the rents and house building rules. The equation related to the property card is as follows.


| Item | Money |
|---|---|
| Cost for buying a property | `baseCost` |
| Cost for building a house |  `baseCost` / 5 |
| Rent for an ordinary property | `baseCost * (1 + house * 0.5)` where `house` denotes the number of houses built on that property |
| Rent for train stations | If the owner has one train station, $500. If the owner has two train stations, $1000. If the owner has three stations, $2000. If the owner has four stations $4000. |
| Rent for utility | If the owner has one utility, the charge is computed by the value of the dice the player rolled multiplied by 10. If the owner has two utilities, the charge is the value of the dice the player rolled multiplied by 100. For example, Alice has owned Library and Canteen. Bob rolls 5 and reach Library. Bob will need to pay Alice 500.  |


## Park Cell

When a player moves to a Car Park, this player will not be able to charge any money from other players until he/she leaves the park. Other players who moves to a property owned by that player does not need to pay the rent.

## Home

When a player passes the Home cell, he/she will receive $2000 from nowhere. If a player move onto the Home Cell, nothing happen.

## Jail

A player moves to a Jail cell (visiting) does nothing.

## Goto Jail

A player moves to a Goto Jail cell will be directly move to the Jail Cell which is at `Gameboard.JAIL_POSITION` position of the cell. The player cannot get $2000 because of passing by the Home cell. The player then will need to suspend for a turn (can't move) during the imprisonment. *Note: a person in jail can still receive rent.*

## Chance and Community Chest

A player moves to a chance or a community chest will incur one of the following outcomes randomly:
* Roll again
* +1000
* -1000
* Being put to Jail (same as Goto Jail)

---

# Classes

The classes `Gameboard`, `GUIGameboard`, and `Cell` are finished. 

| Class Name | Function | Methods to Implement |
|---|---|---|
| `Gameboard` | The mastermind of the program. It has cells and players |  
| `GUIGameboard` | A graphic version alternative of the program. |
| `Cell` | The generic type of cell. This class is an abstract class. | All finished. Don't modify this file. |
| `Player` | A class to model player. A player should have stored his/her name, amount of money he/she has and other possible attributes. | `void roll()` - A player roll a dice and change its position. |
| | |`Player(String name)` - The player constructor that initialize a player. |
| | | `boolean isInPark()` - Return true if the player is in Park |
| | | `void setInPark(boolean inPark)` - Set the player is in Park or not |
| | |`boolean isInJail()` - Return true if the player is in Jail |
| | |`void putToJail()` - Put the player into Jail directly without passing Home (i.e, no 2000) |
| | | `int getMoney()` - Get the amount of money that the player has |
| | | `int getLastMove()` -  Get the value of dice that the player has just rolled. |
| || `String getName()` - Get the name of the player |
| || `int getPosition()` - Get the position of where the player is |
| ||`void charge(int c)` - Charge c dollars from a player. |
| `PropertyCell` | Representing ordinary property cell  | `Player getOwner()` - Return the owner of the cell. Return `null` if it is not owned by any player. |
| | |   `String toString()` - Return a string in the following format: 1) `<property name> owned by <owner's name> : <baseCost>` or 2) `<property name> owned by <owner's name> : <baseCost> House : <house>` Return 1) if the property have no house. Return 2) if the property has more than one house. If the property is not owned, write `-` for the owner's name. |
| | | `void event(Player p, Cell[] cells)` - Trigger the event when a player moves onto this cell. i.e, it should prompt a player to buy an unowned property, or ask the owner to build a house, or make the player to pay rent to the owner (unless the owner is in the park).
| | | `int getRent(Player p)` - Return the rent that this player needs to pay. |
| `TrainStationPropertyCell` | Representing the Train Station cell which can't build any house and the rent is calculated differently. | *Unknown* - not sure what method should this class include</br>. 
| `UtilityPropertyCell` | Representing the utility cell which can't build any house and the rent is calculated differently. | *Unknown* - not sure what method should this class include</br>. 
| `ChanceCell` | Representing the cells Chance and Community Chest. | *Unknown*
| `GotoJailCell` | Representing the cell Goto Jail. | *Unknown*.
| `ParkCell` | Representing the cell Card Park. | *Unknown*.
| `FunctionCell` | Representing the cells Home and Jail. There isn't any effect when a player moves to this cell via **rolling dice**. | *Unknown*