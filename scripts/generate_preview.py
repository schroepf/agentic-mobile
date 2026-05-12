#!/usr/bin/env python3

from pathlib import Path

from PIL import Image, ImageDraw, ImageFont


REPO_ROOT = Path(__file__).resolve().parent.parent
OUTPUT_PATH = REPO_ROOT / "images" / "agentic-mobile-preview.png"
WIDTH, HEIGHT = 900, 1000


def load_font(size: int) -> ImageFont.FreeTypeFont | ImageFont.ImageFont:
    try:
        return ImageFont.truetype("/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf", size)
    except OSError:
        return ImageFont.load_default()


def main() -> None:
    image = Image.new("RGB", (WIDTH, HEIGHT), "#efefef")
    draw = ImageDraw.Draw(image)

    for y in range(HEIGHT):
        shade = int(250 - (y / HEIGHT) * 18)
        draw.line((0, y, WIDTH, y), fill=(shade, shade, shade))

    frame = (255, 78, 645, 922)
    draw.rounded_rectangle(frame, radius=36, fill="white")

    texts = [
        ("Agentic Mobile", load_font(38), "#1c1b1f"),
        ("Kotlin Multiplatform +\nCompose Multiplatform", load_font(22), "#1c1b1f"),
        ("Android 12+ • iOS 16+", load_font(18), "#49454f"),
    ]

    spacing = 16
    heights = []
    for text, font, _ in texts:
        bbox = draw.multiline_textbbox((0, 0), text, font=font, align="center", spacing=6)
        heights.append(bbox[3] - bbox[1])

    total_height = sum(heights) + spacing * (len(texts) - 1)
    y = (frame[1] + frame[3] - total_height) // 2

    for (text, font, color), height in zip(texts, heights):
        bbox = draw.multiline_textbbox((0, 0), text, font=font, align="center", spacing=6)
        text_width = bbox[2] - bbox[0]
        x = frame[0] + ((frame[2] - frame[0]) - text_width) // 2
        draw.multiline_text((x, y), text, font=font, fill=color, align="center", spacing=6)
        y += height + spacing

    OUTPUT_PATH.parent.mkdir(parents=True, exist_ok=True)
    image.save(OUTPUT_PATH)
    print(f"Updated {OUTPUT_PATH}")


if __name__ == "__main__":
    main()
