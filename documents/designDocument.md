# 2D Game

## Description:
2d game with topdown view.
Enemies spawn randomly around the player and walk towards him to damage him.
The player must run about to avoid getting hit and slay the enemies to avoid getting overrun by their number.
In order to deal with enemies the player will be using cards. Every once in a while the player will be dealt a few cards of the deck to use (i.e. slash with the sword, bandage for healing …).
Once the deck has been emptied, the discard pile is shuffled and replaces the deck after some cooldown to avoid spamming cards. There are 2 cooldowns, 1 that is for dealing cards one by one. One longer for when the discord pile gets shuffled.
The player will collect XP and money. These resources will be used to occasionally allow the player to pick new cards to add for the deck, buy upgrades or even remove cards from the deck.
Shopping rounds for cards are between enemy waves.
Player upgrades are upon leveling up.
The game only ends once the player dies, so difficulty will scale until it happens.

## Examples:

### Enemies:

- Slimes, splits into smaller slimes
- Bandit, when it hits the player, adds a wound card into the deck that stays in hand until the player heals himself, at which point the card disappears from the deck.
- Skeleton, ranged enemy that throws bones and forces the player to strafe.
- Mole, digs into the ground and pops up somewhere else, throws mud balls while stationary
- Bat, moves with dashes toward the player

### Cards:

Base cards

- Slash : Circular sword slash in front of the player.
- Bandage: Heals a tiny bit of hp.

Extra cards

- Stink: temporary AOE damage around the player.
- Sprint: faster movement for limited time.
- Pierce: Small dash dealing damage to enemies that were passed through.
- Earthquake: Deal damage to the screen but needs to discard 2 cards.
- Mental block: Adds temporary shield blocking a few projectiles.
- Vampirism: Costs health to play, but steals health back from enemies.
- Scream: Makes the enemies flee for a short instant



Possible Player Upgrades

- Health,
- Damage
- Card cooldown
- Speed
