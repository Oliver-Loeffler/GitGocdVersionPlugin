# GitGocdVersionPlugin for Gradle

When continuous delivery is used, it can be valuable to have reliable version numbers which include the _commit ID_ and a _build ID_ pointing to a certain status in source code history and build pipeline run.

The idea for this plugin evolved as I started to use GoCD for automated builds and continuous delivery.
Especially when artifacts are built from different code repositories, merging binaries and configurations, being able to trace every detail back from the built artifact the last source code change helps a lot in case of looking for errors.

## Semantic Versioning and Git

* MAJOR version when you make incompatible API changes
* MINOR version when you add functionality in a backwards-compatible manner
* PATCH version when you make backwards-compatible bug fixes

A version number might look like: MAJOR.MINOR.PATCH (e.g. 2.0.11)

The idea is, that the PATCH version can be directly taken from Git history.
One could `tag` a specific git commit with an appropriate `MAJOR.MINOR` value.
The count of commits after the last `tag` then represents the `PATCH` version.

Approach:
* declare `MAJOR.MINOR` using `git tag` command
* use commit count after last `tag` as `PATCH` version

## Combining Git with GoCD

Using GoCD so called piplines can be defined to build an artifact.
When a pipeline is running, it will have an id which is store in an environment variable `GO_PIPELINE_COUNTER`.

Now one can create a version such as: MAJOR.MINOR.PATCH.__BUILD__

This means, that just re-running the build pipeline, a new version number is created, which will help then to separate if a problem is caused by an erroneous build or by a bug in source code.
Another point is, that when the GoCD environment `GO_PIPELINE_COUNTER` is not detected, a deployment operation could be suppressed. This means, that e.g. local builds cannot be published into remote repositories.

# How does the Plugin work?

Ideally:
* just apply the plugin
* to suppress publish operations, provide example
    
## TODO:

1. Read last `tag` from Git or provide `undefined` version
2. Read `GO_PIPELINE_COUNTER` environment variable (if desired)
3. Create version like `tag`.`GO_PIPELINE_COUNTER` and define this as the given Gradle project version
4. Provide a hook to suppress publishing of artifacts to remote repositories
5. Make the `GO_PIPELINE_COUNTER` variable name configurable



## License

Copyright 2019 Oliver Löffler

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
