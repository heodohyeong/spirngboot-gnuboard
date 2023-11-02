package com.gnuboard.demo.board.domain;


import com.gnuboard.demo.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardFile extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bf_id")
    private Long id;

//    @Column(name = "bp_id")
//    private Long boardPostId;

    @Column(name = "bf_source")
    private String source;

    @Column(name = "bf_file")
    private String file;

    @Column(name = "bf_download")
    private int download;

    @Column(name = "bf_content")
    private String content;

    @Column(name = "bf_filesize")
    private int filesize;

    @Column(name = "bf_original_name")
    private String originalName;
    @Column(name = "bf_saved_name")
    private String savedName;
    @Column(name = "bf_extension_name")
    private String extensionName;

    @OneToOne(mappedBy = "boardFile")
    private PostFile postFile;


    @Override
    public String toString() {
        return "BoardFile{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", file='" + file + '\'' +
                ", download=" + download +
                ", content='" + content + '\'' +
                ", filesize=" + filesize +
                '}';
    }
}
