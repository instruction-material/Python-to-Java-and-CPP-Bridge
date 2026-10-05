# Python to Java and C++ Bridge

Starter and solution code for the Python to Java and C++ Bridge course.

The repo contains:
- dedicated `PTJ*` bridge builds
- migrated `BRGX*` supplemental bridge labs
- bridge-specific applied studios used by the live classes catalog

Shared Java and C++ foundation builds now live in their owning course repos:
- `Java-Level-1` owns `J1X01-java-foundations-build-12`
- `C-Level-1-C-Fundamentals` owns `CPP-01-c-foundations-build-13`
- `CPP-Level-1` also owns `CPP_practice`

This repo keeps only bridge-specific materials plus the local `graphics/` support
folder used by the bridge's Java transition exercises.

## Verified original ports

Start with `PTJ1` through `PTJ4`, choose the Java `PTJ5` or C++ `PTJ6` exit,
and use the complete learner brief included in each starter import. Java 21,
C++17 and Python 3 are sufficient for the dependency-free native source gate:
`bash verify-course-source.sh`. Optional `JAVAC`, `JAVA` and `CXX` environment
variables can select installed compilers. See `SOURCE_PACK_REVIEW.md` for the
precise tested boundary and outstanding generated-pack/catalog mismatches.

## Chosen-language capstone

`PTJ7-Task-Tracker-Capstone` ports one complete Python task tracker into Java or
C++. Its source/import brief contains all six method contracts and console
fixtures. Choose `starter/java` or `starter/cpp`; completed target-language
answers remain separately under `solution/`. Use the native compiler commands
for the object-based project. BRG scoring clones and JavaFX examples remain
historical/support material; see `SOURCE_BACKLOG.md` and `SOURCE_PACK_REVIEW.md`.
