### Branching strategy
Our branching strategy follows the Github-flow model. We maintain one `main` branch with feature branches derived from it. Feature branches are worked on by only one contributor each, and are merged back into `main` via merge requests. Each merge request must be reviewed by at least one other project contributor before being approved and merged.

Not every commit on `main` represents a production-ready state; these are indicated by releases, which are a subset of the commits on `main`.

The only changes to `main` that may be committed outside a merge request from a feature branch are changes to project documentation, under the `docs/` directory (like this file!)

### Naming convention
Feature branches shall be named `az/dev99`, where `az` are the initials of the single contributor working on this branch, and `99` is the issue number being addressed by this branch. Each feature branch should ideally only address one development task (not one whole feature, as the name might suggest).

