# Sample Source Inventory

This file records the source, license, preparation, manual judgment, and
expected app behavior for the external comparison samples in `samples/`.

All online samples were downloaded from Wikimedia Commons. They are not Redmi
K70E self-shot images. They may be used as official comparison samples because
the contest requires comparison samples and does not require all samples to be
self-shot. Keep the source page, author, and license with the final submission.

## Category Samples

| File | Category | Source Page | Author | License | Preparation | Manual Judgment | Expected App Behavior |
|---|---|---|---|---|---|---|---|
| `clear/clear_butterfly_01.jpg` | Clear | <https://commons.wikimedia.org/wiki/File:Lesser_grass_blue_(Zizina_otis)_on_Hibiscus_flower_Koror.jpg> | Charles J. Sharp | CC BY-SA 4.0 | Downloaded 1920px Wikimedia rendition | Clear, sharp subject, normal exposure | High sharpness and overall score |
| `clear/clear_beetle_spider_02.jpg` | Clear | <https://commons.wikimedia.org/wiki/File:Bee_beetle_(Trichius_fasciatus)_and_flower_crab_spider_(Thomisidae_sp).jpg> | Charles J. Sharp | CC BY-SA 4.0 | Downloaded 1920px Wikimedia rendition | Clear macro/detail image | High sharpness and normal exposure |
| `clear/clear_butterfly_03.jpg` | Clear | <https://commons.wikimedia.org/wiki/File:Large_white_(Pieris_brassicae)_underside.jpg> | Charles J. Sharp | CC BY-SA 4.0 | Downloaded 1920px Wikimedia rendition | Clear focused subject | High sharpness and normal contrast |
| `blur/blur_subway_motion_01.jpg` | Blur | <https://commons.wikimedia.org/wiki/File:Man_at_Dundas_subway_station,_Toronto,_2008-05-06.jpg> | Radomianin | CC BY-SA 4.0 | Downloaded Wikimedia JPEG | Motion blur from moving train | Lower sharpness score |
| `blur/blur_tanoura_motion_02.jpg` | Blur | <https://commons.wikimedia.org/wiki/File:Folkloric_Tanoura_dance_performed_by_man.jpg> | Mohamed Khalil90 | CC BY-SA 4.0 | Downloaded 1920px Wikimedia rendition | Intentional motion blur | Lower sharpness, possible acceptable exposure |
| `blur/blur_out_of_focus_03.jpg` | Blur | <https://commons.wikimedia.org/wiki/File:An_example_of_an_out_of_focus_photograph.jpg> | Artem Katranzhi | CC BY-SA 2.0 | Downloaded 1920px Wikimedia rendition | Out-of-focus image | Low sharpness warning |
| `overexposed/overexposed_photo_01.jpg` | Overexposed | <https://commons.wikimedia.org/wiki/File:Overexposed_photo.JPG> | Dawid Skalec | CC BY-SA 4.0 | Downloaded 1920px Wikimedia rendition | Washed-out highlights | Exposure warning |
| `overexposed/overexposed_sky_02.jpg` | Overexposed | <https://commons.wikimedia.org/wiki/File:5593_with_overexposed_sky.jpg> | Hugh Llewellyn | CC BY-SA 2.0 | Downloaded 1920px Wikimedia rendition | Overexposed sky | Exposure warning |
| `overexposed/overexposed_space_washed_03.jpg` | Overexposed | <https://commons.wikimedia.org/wiki/File:S42-209-015_-_STS-042_-_Overexposed_Photograph_-_DPLA_-_c75093b81f7c49ebc0e01c7c28fed192.jpg> | NASA / Lyndon B. Johnson Space Center | Public domain | Downloaded 1920px Wikimedia rendition | Washed-out overexposed space photograph | Exposure warning |
| `underexposed/underexposed_sun_01.jpg` | Underexposed | <https://commons.wikimedia.org/wiki/File:Underexposed_Sun.jpg> | Danecarney | CC0 | Downloaded 1920px Wikimedia rendition | Very dark frame | Exposure warning |
| `underexposed/underexposed_space_02.jpg` | Underexposed | <https://commons.wikimedia.org/wiki/File:Mercury_V_mission_-_Underexposed_-_DPLA_-_819efa7aa0348d0bb3d7ba467a9f5f7a.jpg> | NASA / Lyndon B. Johnson Space Center | Public domain | Downloaded 1920px Wikimedia rendition | Underexposed space photograph | Exposure warning |
| `underexposed/underexposed_pollen_03.jpg` | Underexposed | <https://commons.wikimedia.org/wiki/File:Underexposed_pollen_flower_(25413776865).jpg> | Raita Futo | CC BY 2.0 | Downloaded 1920px Wikimedia rendition | Dark low-exposure image | Exposure warning |
| `noisy/noisy_hong_kong_night_01.jpg` | Noisy | <https://commons.wikimedia.org/wiki/File:Hong_Kong_in_night_(noise_version).jpg> | Wilfredor | CC0 | Downloaded 1920px Wikimedia rendition | Night image with visible noise | Noise/color/contrast limitation candidate |
| `noisy/noisy_nind_stefantiek_02.jpg` | Noisy | <https://commons.wikimedia.org/wiki/File:NIND_stefantiek_ISOH1.jpg> | Trougnouf | CC BY 4.0 | Downloaded Wikimedia JPEG | Natural Image Noise Dataset sample | Noise limitation candidate |
| `noisy/noisy_nind_keyboard_03.jpg` | Noisy | <https://commons.wikimedia.org/wiki/File:NIND_parking-keyboard_ISOH3.jpg> | Trougnouf | CC BY 4.0 | Downloaded 1920px Wikimedia rendition | High-ISO noise sample | Noise limitation candidate |
| `counterexamples/counterexample_bokeh_01.jpg` | Counterexample | <https://commons.wikimedia.org/wiki/File:Out-of-focus_dashboard_and_traffic_lights_at_night_02.jpg> | A S M Jobaer | CC BY-SA 4.0 | Downloaded 1920px Wikimedia rendition | Intentional night bokeh, human-acceptable | May be penalized for blur |
| `counterexamples/counterexample_colored_lights_02.jpg` | Counterexample | <https://commons.wikimedia.org/wiki/File:Niagara_Falls_by_night_July_2010_03.JPG> | Michael Barera | CC BY-SA 4.0 | Downloaded 1920px Wikimedia rendition | Colored night lighting, human-acceptable | May trigger color-cast/exposure warning |
| `counterexamples/counterexample_sunset_03.jpg` | Counterexample | <https://commons.wikimedia.org/wiki/File:Grand_Canyon_Warm_Sunset_Light.jpg> | Eric Kilby | CC BY-SA 2.0 | Downloaded 1920px Wikimedia rendition | Warm sunset light, human-acceptable | May be judged as color cast |

## Format Samples

| File | Source | License | Preparation |
|---|---|---|---|
| `formats/format_clear_jpeg_01.jpg` | Derived from `clear/clear_butterfly_01.jpg` | CC BY-SA 4.0, Charles J. Sharp | Copied JPEG for format validation |
| `formats/format_clear_png_01.png` | Derived from `clear/clear_butterfly_01.jpg` | CC BY-SA 4.0, Charles J. Sharp | Converted locally to PNG with Pillow |
| `formats/format_clear_webp_01.webp` | Derived from `clear/clear_butterfly_01.jpg` | CC BY-SA 4.0, Charles J. Sharp | Converted locally to WebP with Pillow |

## Notes

- These images satisfy the contest comparison sample requirement with 3 files
  each for clear, blur, overexposed, underexposed, and noisy categories.
- Three counterexamples are included for later WS6 analysis.
- All app scores remain pending until WS6 runs the Android app against these
  samples.
