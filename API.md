# API docs

- **GET** - get current data
- **POST** - create new data
- **PATCH** - update current data
- **DELETE** - delete current data

## Note

<!-- MARK: Note -->
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------ -->

### `GET` notes by `author_id`

`/api/notes?author_id={author_id}`

**Response** (200 OK):

```json
[
  {
    "id": "423",
    "author_id": "",
    "title": "Первая заметка",
    "text": "Это моя первая тестовая заметка. Здесь я проверяю работу системы.",
    "created_at": "2024-01-15 10:30:00"
  },
  {
    "id": "42423",
    "author_id": "",
    "title": "Список покупок",
    "text": "Молоко, хлеб, яйца, сыр, фрукты",
    "created_at": "2024-01-16 14:25:00"
  }
]
```

### `GET` notes by `search_query`

`/api/notes?search={query}&author_id={author_id}`

**Response** (200 OK):

```json
[
  {
    "id": "423",
    "author_id": "",
    "title": "Первая заметка",
    "text": "Это моя первая тестовая заметка. Здесь я проверяю работу системы.",
    "created_at": "2024-01-15 10:30:00"
  },
  {
    "id": "42423",
    "author_id": "",
    "title": "Список покупок",
    "text": "Молоко, хлеб, яйца, сыр, фрукты",
    "created_at": "2024-01-16 14:25:00"
  }
]
```

### `GET` note by `note_id`

`/api/notes?id={note_id}`

**Response** (200 OK):

```json
{
  "id": "423",
  "author_id": "",
  "title": "Первая заметка",
  "text": "Это моя первая тестовая заметка. Здесь я проверяю работу системы.",
  "created_at": "2024-01-15 10:30:00"
}
```

### `POST` note by `author_id`

`/api/notes?author_id={author_id}`

**Request Body**:

```json
{
  "title": "Первая заметка",
  "text": "Это моя первая тестовая заметка. Здесь я проверяю работу системы."
}
```

### `PATCH` note by `author_id`

`/api/notes?author_id={author_id}`

**Request Body**:

(at least one field)

```json
{
  "title": "Первая заметка",
  "text": "Это моя первая тестовая заметка. Здесь я проверяю работу системы."
}
```

### `DELETE` note by `note_id`

`/api/notes?id={note_id}`

## User

<!-- MARK: User -->
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------ -->

### `GET` users

`/api/users`

**Response** (200 OK):

```json
[
  {
    "id": "1",
    "short_name": "ivanov",
    "name": "Иванов Иван Иванович"
  },
  {
    "id": "2",
    "short_name": "petrov",
    "name": "Петров Петр Петрович"
  }
]
```

### `GET` user by `user_id`

`/api/users?id={user_id}`

**Response** (200 OK):

```json
{
  "id": "1",
  "short_name": "ivanov",
  "name": "Иванов Иван Иванович"
}
```

### `POST` user by `user_id`

`/api/users?id={user_id}`

**Request Body**:

```json
{
  "short_name": "ivanov",
  "name": "Иванов Иван Иванович"
}
```

### `DELETE` user by `user_id`

`/api/users?id={user_id}`
