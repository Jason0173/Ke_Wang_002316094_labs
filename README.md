# Java Swing Coursework

Labs and assignments from an object-oriented Java course at Northeastern University (Spring 2025). Most projects are desktop apps built with Java Swing in the NetBeans GUI builder, with the data model (`model/`) kept separate from the screens (`ui/`).

## Assignments

| Project | What it does |
|---|---|
| [`assignment2-vehicle-service`](assignments/assignment2-vehicle-service/) | Vehicle service manager: register a vehicle with its owner and the service booked, then search, view and delete records |
| [`assignment3-library-management`](assignments/assignment3-library-management/) | Multi-branch library system with system admin, branch manager and customer roles: create and delete branches, add books, and rent them, with each rental tracked as rented or returned |

## Labs

| Lab | Topic |
|---|---|
| [`lab1-product-form`](labs/lab1-product-form/) | First Swing app: create a product and view it on a second panel |
| [`lab3-account-manager`](labs/lab3-account-manager/) | Bank account directory with create, search, view, update and delete |
| [`lab4-supplier-catalog`](labs/lab4-supplier-catalog/) | Login plus admin and supplier work areas for managing suppliers and product catalogs |
| [`lab7-marketplace-orders`](labs/lab7-marketplace-orders/) | Three-role marketplace (admin, supplier, customer) with product browsing, a cart and orders |
| [`lab9-social-media-analytics`](labs/lab9-social-media-analytics/) | Console app that loads generated users, posts and comments from CSV and answers analytics questions, such as the most-commented post and the least active users |

## Run

**In NetBeans:** File → Open Project, then pick a folder under `labs/` or `assignments/`.

**From the command line** (JDK 11 or later):

```bash
cd assignments/assignment3-library-management
javac -d out $(find src -name '*.java')
java -cp out SwingGUIApplication.LibraryManagementSystemApp
```

Every other project has `ui.MainJFrame` as its main class, except `lab9-social-media-analytics`, whose main class is `main.SocialMedia_Main`. Run lab 9 from its own folder, because it reads its CSV files from there and regenerates them on every run. `lab1-product-form` has no `src/` folder, so compile it with `find . -name '*.java'` instead.
