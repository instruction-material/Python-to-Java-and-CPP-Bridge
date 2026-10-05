# Course Source Manifest

Canonical source repository: `Python-to-Java-and-CPP-Bridge`

## Mapped Course and Assignment Roles

- `python-to-java-and-cpp-bridge`: Python to Java and C++ Bridge
- Shared foundation: PTJ1–PTJ4, each with a Java or C++ starter/reference branch.
- Choose one exit: Java PTJ5 or C++ PTJ6.
- Chosen-language capstone: PTJ7, with a supplied Python task tracker and separate
  incomplete Java/C++ starters and target-language references.

These are source assignment roles. The accompanying catalog correction must
retire the historical links and expose explicit target-language choices. This
manifest does not establish production activation or browser execution support.

## Verification Gate

Run `bash verify-course-source.sh` with installed Python 3, Java 21 and C++17.
The native gate compiles all 24 starter/reference programs: 20 original PTJ
programs and four authored capstone programs. Independent API and state-model
checks cover unfinished starter methods, function contracts, collection ordering,
object state, rejected operations, copy/alias behavior and real console input.
The Java site runner is a limited preview; the object-based capstone requires
native Java compilation. C++ uses the documented native compiler workflow.

## Reviewed Assignment Folders

| Folder | Role |
| --- | --- |
| `PTJ1-Syntax-Translation-Warmup` | Shared syntax port |
| `PTJ2-Function-Port-Pack` | Shared function port |
| `PTJ3-Text-and-Collection-Port-Lab` | Shared collection port |
| `PTJ4-Shared-Class-Port` | Shared class port |
| `PTJ5-Python-to-Java-Quiz-Game` | Java exit choice |
| `PTJ6-Python-to-CPP-Console-Port` | C++ exit choice |
| `PTJ7-Task-Tracker-Capstone` | Chosen-language stateful capstone |

## Retained Historical/Support Folders

Forty BRG scoring clones and the JavaFX support folder are retained for source
history. They are outside the reviewed assignment gate and must not be presented
as distinct reviewed course projects. See `SOURCE_BACKLOG.md` for every path.
Their original source files are unchanged by the capstone addition.

## Course-folder Inventory

- Source course folders: 48
- Reviewed assignment folders: 7
- Retained historical/support folders: 41
- Original source filenames and paths are retained.
