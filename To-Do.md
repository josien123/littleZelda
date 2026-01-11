# To-Do
This is an action-oriented To-Do derived from `projectRequirements.md` and the current codebase. I won't modify Java without your approval — these are the next design and implementation steps.

## Top priorities (design)
- [ ] Design `Location` subclasses: `Building`, `Park`, `Shop` — fields and `enter(player)`/`exit(player)` effects (30–60m)
- [ ] Design `Item` hierarchy: `Item`, `Food`, `Tool`, `Treasure` — `value` and `use()` semantics (30–45m)
- [ ] Draft `Player` API: `move(direction)`, `pickUpItem(item)`, `useItem(item)`, `talkTo(npc)` (30m)

## This Sprint (implementation after approval)
- [ ] Decide map size (5x5 or 7x7) and update map generation plan (15m)
- [ ] Centralize movement into `Player.move(direction)` and bounds checking (1–2h)
- [ ] Define shop mechanics and currency model (45–60m)
- [ ] Add `Item` subclasses and inventory `use()` semantics (1h)
- [ ] Write README skeleton


- [ ] Write unit tests for core logic
- [ ] Create manual test cases for: movement, inventory, NPC encounters, item use, and end conditions (30–60m)
- [ ] Create a short playthrough checklist to validate commands and messages (15–30m)

- [ ] Level design + enemy AI

## Notes
- [ ] Update `diagrams/classes.puml` to reflect approved designs and render PNG/SVG (15–30m)
- [ ] Fill in reflection & self-grading in `projectRequirements.md` (20–30m)
- [ ] Update README with commands, goals, and quick start (20–30m)

- Priority: P0 = must, P1 = next, P2 = low
- Priorities: P0 = must, P1 = next, P2 = low
- Pick one design item to approve and I'll produce method signatures and a step-by-step patch plan.