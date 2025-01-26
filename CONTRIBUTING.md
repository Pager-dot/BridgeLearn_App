# Contributing to BridgeLearn

First off, thanks for taking the time to contribute! ❤️

Contributions are always welcome, no matter how large or small. Before contributing, please read the [Code of Conduct](#code-of-conduct) and follow the directions in this guide.

## Table of Contents

- [Introduction](#introduction)
- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Areas for Contribution](#areas-for-contribution)
- [Branching Strategy](#branching-strategy)
- [Code Style and Standards](#code-style-and-standards)
- [Testing Guidelines](#testing-guidelines)
- [Documentation Updates](#documentation-updates)
- [Submitting Issues](#submitting-issues)
- [Pull Request Guidelines](#pull-request-guidelines)
- [Commit Message Format](#commit-message-format)
- [Project Structure](#project-structure)
- [Acknowledgments](#acknowledgments)
- [License](#license)

## Introduction <a name="introduction"></a>
Welcome to BridgeLearn! We're excited that you're interested in contributing. This document provides guidelines and instructions for contributing to the project. Our goal is to create an inclusive, collaborative learning platform that helps students master bridge concepts effectively.

## Code of Conduct <a name="code-of-conduct"></a>

### Our Pledge

In the interest of fostering an open and welcoming environment, we as
contributors and maintainers pledge to make participation in our project and
our community a harassment-free experience for everyone, regardless of age, body
size, disability, ethnicity, sex characteristics, gender identity and expression,
level of experience, education, socio-economic status, nationality, personal
appearance, race, religion, or sexual identity and orientation.

### Our Standards

Examples of behaviour that contributes to a positive environment for our
community include:

* Demonstrating empathy and kindness toward other people
* Being respectful of differing opinions, viewpoints, and experiences
* Giving and gracefully accepting constructive feedback
* Accepting responsibility and apologising to those affected by our mistakes,
and learning from the experience
* Focusing on what is best not just for us as individuals, but for the
overall community

Examples of unacceptable behaviour include:

* The use of sexualised language or imagery, and sexual attention or advances
* Trolling, insulting or derogatory comments, and personal or political attacks
* Public or private harassment
* Publishing others' private information, such as a physical or email
address, without their explicit permission
* Other conduct which could reasonably be considered inappropriate in a
professional setting

### Our Responsibilities

Project maintainers are responsible for clarifying and enforcing our standards of
acceptable behaviour and will take appropriate and fair corrective action in
response to any instances of unacceptable behaviour.

Project maintainers have the right and responsibility to remove, edit, or reject
comments, commits, code, wiki edits, issues, and other contributions that are
not aligned to this Code of Conduct, or to ban
temporarily or permanently any contributor for other behaviours that they deem
inappropriate, threatening, offensive, or harmful.

### Scope

This Code of Conduct applies within all community spaces, and also applies when
an individual is officially representing the community in public spaces.
Examples of representing our community include using an official e-mail address,
posting via an official social media account, or acting as an appointed
representative at an online or offline event.

### Enforcement

Instances of abusive, harassing, or otherwise unacceptable behaviour may be
reported to the community leaders responsible for enforcement at [@pratheekv39](https://github.com/pratheekv3).
All complaints will be reviewed and investigated promptly and fairly.

All community leaders are obligated to respect the privacy and security of the
reporter of any incident.

### Attribution

This Code of Conduct is adapted from the [Contributor Covenant](https://contributor-covenant.org/), version
[1.4](https://www.contributor-covenant.org/version/1/4/code-of-conduct/code_of_conduct.md) and
[2.0](https://www.contributor-covenant.org/version/2/0/code_of_conduct/code_of_conduct.md).

## Getting Started <a name="getting-started"></a>
### Prerequisites
- Android Studio Hedgehog | 2023.1.1
- Kotlin 1.9.0
- Minimum SDK: 24
- Target SDK: 34

### Setup
1. Fork the repository
2. Clone your fork:
```bash
git clone https://github.com/YOUR-USERNAME/BridgeLearn_App.git
```
3. Add upstream remote:
```bash
git remote add upstream https://github.com/pratheekv39/BridgeLearn_App.git
```
4. Create a new branch for your feature:
```bash
git checkout -b feature/your-feature-name
```

### Build and Run
1. Open project in Android Studio
2. Sync Gradle files
3. Run on emulator or physical device

## Areas for Contribution <a name="areas-for-contribution"></a>
We welcome contributions in these areas:
- Learn Module
  - Content creation
  - Interactive exercises
  - Progress tracking
- Quiz System
  - Question bank expansion
  - Assessment algorithms
  - Performance analytics
- Community Features
  - Discussion forums
  - Peer learning tools
- Accessibility
  - UI/UX improvements
  - Screen reader compatibility
  - Language support

## Branching Strategy <a name="branching-strategy"></a>
Use the following branch naming conventions:
- `feature/[feature-name]` - For new features
- `fix/[bug-name]` - For bug fixes
- `docs/[document-name]` - For documentation
- `test/[test-name]` - For testing improvements

## Code Style and Standards <a name="code-style-and-standards"></a>
- Follow Kotlin coding conventions
- Implement MVVM architecture patterns
- Use meaningful variable and function names
- Include comments for complex logic
- Follow Android Material Design guidelines
- Maintain separation of concerns

## Testing Guidelines <a name="testing-guidelines"></a>
All contributions must include appropriate tests:
- Unit tests for business logic
- Integration tests for component interaction
- UI tests for user interactions
- Minimum 80% code coverage for new features
- All tests must pass before PR submission

## Documentation Updates <a name="documentation-updates"></a>
When updating documentation:
- Keep README.md current
- Update inline code comments
- Document new features
- Update API documentation
- Include usage examples
- Update version changelog

## Submitting Issues <a name="submitting-issues"></a>
When creating an issue:
1. Use the provided issue template
2. Include clear reproduction steps
3. Specify environment details
4. Add relevant labels
5. Include screenshots if applicable

## Pull Request Guidelines <a name="pull-request-guidelines"></a>
We actively welcome your PRs. However, before working on changes, you must ensure that **you are assigned** to an existing issue and **link your work to the issue in your PR template**.

### Before Submitting a PR Template

1. Ensure that your changes are made in a new branch.

2. Run and check your changes locally. Ensure that everything works as it should.

### Submitting a PR Template

1. Ensure that you address one issue in one PR. <br> If you work on multiple issues, work on them separately and create one PR to address each issue.

2. Completing the PR template. Make sure you **fill in all sections** and that you have:

   - **A valid title**. The PR title must begin with `feature:`, `fix:`, or anything related to your changes.
   - **A related issue**. [Link the issue number](https://docs.github.com/en/issues/tracking-your-work-with-issues/linking-a-pull-request-to-an-issue) that you worked on and add a keyword of "Closes", "Fixes", or "Resolves" in front of it. For example: Closes #123, Fixes #234, etc.

3. Do NOT delete any section of the PR template. <br> If a section is irrelevant to your changes, please explain or respond with "N/A".

### After Submitting a PR Template

1. Ensure that all checks are passed. <br> If you see any GitHub action bots or checks that failed after you submit your PR template, you need to read each one and understand why it failed. Then, you must address and fix it until all of them pass.

2. Do NOT DM maintainers or tag them in the comments to review your PR. <br> Maintainers are always notified whenever there is an incoming PR. If you haven't received a review within a week, please tag them in the PR comments to ask for an estimated review time.

3. Keep your branch up to date while waiting for review and resolve any merge conflicts in your terminal.

4. Response and address the reviewer's feedback.

### ⚠️ A PR will be marked as invalid and may be closed if:

- the issue is not assigned to the contributor who opened the PR.
- no issue is linked to the PR.
- PR template is incomplete, or any section in the template is deleted.
- changes are made directly in the default (`main`) branch.

## Commit Message Format <a name="commit-message-format"></a>
Follow conventional commits:

## Project Structure <a name="project-structure"></a>
```
app/
├── src/
│   ├── main/
│   │   ├── java/pratheekv39/bridgelearn/io/
│   │   │   ├── ui/
│   │   │   │   ├── home/
│   │   │   │   ├── learn/
│   │   │   │   ├── quiz/
│   │   │   │   ├── community/
│   │   │   │   └── profile/
│   │   │   ├── data/
│   │   │   ├── domain/
│   │   │   └── di/
│   │   └── res/
│   └── test/
└── build.gradle.kts
```

## Acknowlegments <a name="acknowledgments"></a>
Use this space to list resources you find helpful and would like to give credit to. 

## License <a name="license"></a>

### MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
