## ADDED Requirements

### Requirement: Local image selection
The app SHALL allow the user to select a local image from the device album or file picker.

#### Scenario: User selects an image
- **WHEN** the user chooses an image from local storage
- **THEN** the app displays the selected image in the preview area

#### Scenario: User cancels selection
- **WHEN** the user opens the picker and cancels without selecting a file
- **THEN** the app remains stable and keeps the previous state or empty state

### Requirement: Required image format support
The app MUST support decoding and previewing JPEG, PNG, and WebP images.

#### Scenario: JPEG image loads
- **WHEN** the user selects a JPEG image
- **THEN** the app previews the image and marks the format as JPEG

#### Scenario: PNG image loads
- **WHEN** the user selects a PNG image
- **THEN** the app previews the image and marks the format as PNG

#### Scenario: WebP image loads
- **WHEN** the user selects a WebP image
- **THEN** the app previews the image and marks the format as WebP

### Requirement: Image metadata display
The app SHALL display basic metadata for the selected image, including format, dimensions, and file size when available.

#### Scenario: Metadata is available
- **WHEN** the app finishes loading a selected image
- **THEN** the app displays image format, width, height, and file size

### Requirement: Large image stability
The app MUST decode large images safely by using an analysis bitmap that is downsampled from the original image.

#### Scenario: Large image is selected
- **WHEN** the user selects an image with resolution greater than or equal to 12MP
- **THEN** the app analyzes a downsampled bitmap and avoids an out-of-memory crash

