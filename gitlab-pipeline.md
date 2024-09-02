
```
# .gitlab-ci.yml

stages:
  - build
  - test

build:
  stage: build
  script:
    - echo "Building..."
    # Build your code here

test:
  stage: test
  script:
    - echo "Testing..."
    # Run your tests here

release:
  stage: deploy
  script:
    - echo "Deploying..."
    # Deploy your code here
  rules:
    - if: '$CI_COMMIT_TAG'

```

```
# .gitlab-ci.yml

variables:
  PACKAGE_NAME: "my-package-${CI_COMMIT_TAG}"

build_package:
  stage: build
  script:
    - echo "Building package: $PACKAGE_NAME"
    # Build and package your code using the $PACKAGE_NAME

```
