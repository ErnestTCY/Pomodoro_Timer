# Coding Standards Memory Bank

## Java Development Guidelines

### Package Structure
```
com.pomodorotimer
├── core/           # Core timer logic
├── ui/             # User interface components  
├── notification/   # Notification system
├── config/         # Configuration management
└── util/           # Utility classes
```

### Naming Conventions
- Classes: PascalCase (e.g., `SessionManager`, `NotificationService`)
- Methods: camelCase (e.g., `startSession()`, `showNotification()`)
- Constants: UPPER_SNAKE_CASE (e.g., `DEFAULT_WORK_MINUTES`)
- Packages: lowercase (e.g., `com.pomodorotimer.core`)

### Key Design Principles
- **Single Responsibility**: Each class has one clear purpose
- **Observer Pattern**: For session state changes and notifications
- **Strategy Pattern**: For different notification types
- **Builder Pattern**: For configuration objects

### Error Handling
- Use checked exceptions for recoverable errors
- Runtime exceptions for programming errors
- Proper logging with different levels (INFO, WARN, ERROR)
- Graceful degradation for notification failures

### Threading Guidelines
- Use `SwingUtilities.invokeLater()` for UI updates
- Separate timer thread from UI thread
- Proper synchronization for shared state
- Handle thread interruption gracefully